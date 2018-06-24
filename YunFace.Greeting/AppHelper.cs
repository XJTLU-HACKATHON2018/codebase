using Emgu.CV;
using Emgu.CV.CvEnum;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Configuration;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Media;
using System.Windows.Media.Imaging;

namespace YunFace.Greeting
{
    public class AppHelper : YunFace.Client.AppHelper
    {
        [System.Runtime.InteropServices.DllImport("gdi32")]
        private static extern int DeleteObject(IntPtr o);

        public const string DateTimeFormat = "yyyy-MM-dd HH:mm:ss";
        public const string TimeFormat = "HH:mm";
        public static readonly BitmapSource DefaultBitmap = ConvertToBitmapSource("images/default.png", UriKind.Relative);

        public static readonly string AppId = ConfigurationManager.AppSettings["AppId"];
        public static readonly string AppVersion = ConfigurationManager.AppSettings["AppVersion"];

        public static readonly string CaptureSource = ConfigurationManager.AppSettings["CaptureSource"];
        public static readonly long DeviceId = long.Parse(ConfigurationManager.AppSettings["DeviceId"]);
        public static readonly int IdentifyingHistoryCount = int.Parse(ConfigurationManager.AppSettings["IdentifyingHistoryCount"]);
        public static readonly int PlayingQueueLength = int.Parse(ConfigurationManager.AppSettings["PlayingQueueLength"]);
        public static readonly int MatchingFaceCount = int.Parse(ConfigurationManager.AppSettings["MatchingFaceCount"]);
        public static readonly string DefaultGreetingWords = ConfigurationManager.AppSettings["DefaultGreetingWords"];
        public static readonly string DefaultTag = ConfigurationManager.AppSettings["DefaultTag"];
        public static readonly double VideoBoxOpacity = double.Parse(ConfigurationManager.AppSettings["VideoBoxOpacity"]);
        public static readonly string IdentityMask = ConfigurationManager.AppSettings["IdentityMask"];
        public static readonly bool IdentitySimilarity = bool.Parse(ConfigurationManager.AppSettings["IdentitySimilarity"]);
        private static readonly Lazy<string[]> _adFiles = new Lazy<string[]>(() =>
        {
            const string AdsDirectory = "ads";

            var adsDirectory = Path.Combine(AppPath, AdsDirectory);
            EnsureDirectory(adsDirectory);
            return Directory.GetFiles(adsDirectory, "*.*", SearchOption.TopDirectoryOnly)
                .ToArray();
        });
        public static string[] AdFiles { get { return _adFiles.Value; } }
        public static readonly int AdsInterval = int.Parse(ConfigurationManager.AppSettings["AdsInterval"]);
        public static readonly double AdsVolume = double.Parse(ConfigurationManager.AppSettings["AdsVolume"]);

        public static readonly bool CommandByShell = bool.Parse(ConfigurationManager.AppSettings["CommandByShell"]);
        public static readonly bool CommandWithWindow = bool.Parse(ConfigurationManager.AppSettings["CommandWithWindow"]);
        public static readonly string[] CommandOnAuthenticated = ConfigurationManager.AppSettings["CommandOnAuthenticated"].Split(new string[] { ItemDelimiter }, StringSplitOptions.RemoveEmptyEntries);
        public static readonly string[] CommandArgsOnAuthenticated = ConfigurationManager.AppSettings["CommandArgsOnAuthenticated"].Split(new string[] { ItemDelimiter }, StringSplitOptions.None);
        public static readonly string[] CommandArgsEscaping = ConfigurationManager.AppSettings["CommandArgsEscaping"].Split(new string[] { KeyValueDelimiter }, StringSplitOptions.None);

        public static BitmapSource ConvertToBitmapSource(Bitmap bitmap)
        {
            IntPtr ptr = bitmap.GetHbitmap(); //obtain the Hbitmap
            BitmapSource bs = System.Windows.Interop.Imaging.CreateBitmapSourceFromHBitmap(
                ptr,
                IntPtr.Zero,
                Int32Rect.Empty,
                BitmapSizeOptions.FromEmptyOptions());
            DeleteObject(ptr); //release the HBitmap
            return bs;
        }
        public static BitmapSource ConvertToBitmapSource(IImage image)
        {
            return ConvertToBitmapSource(image.Bitmap);
        }
        public static Mat ConvertToMat(BitmapSource source)
        {
            if (source.Format == PixelFormats.Bgra32)
            {
                Mat result = new Mat();
                result.Create(source.PixelHeight, source.PixelWidth, DepthType.Cv8U, 4);
                source.CopyPixels(Int32Rect.Empty, result.DataPointer, result.Step * result.Rows, result.Step);
                return result;
            }
            else if (source.Format == PixelFormats.Bgr24)
            {
                Mat result = new Mat();
                result.Create(source.PixelHeight, source.PixelWidth, DepthType.Cv8U, 3);
                source.CopyPixels(Int32Rect.Empty, result.DataPointer, result.Step * result.Rows, result.Step);
                return result;
            }
            else
            {
                throw new Exception(String.Format("Convertion from BitmapSource of format {0} is not supported.", source.Format));
            }
        }
        public static BitmapSource ConvertToBitmapSource(string uri, UriKind uriKind)
        {
            try
            {
                return new BitmapImage(new Uri(uri, uriKind));
            }
            catch
            {
                return DefaultBitmap;
            }
        }

        #region 基于缓存的 WPF 图片服务

        private static ConcurrentDictionary<string, BitmapSource> BitmapSourceCache = new ConcurrentDictionary<string, BitmapSource>();
        public static BitmapSource GetBitmapSource(string uri, string baseUri = null)
        {
            if (string.IsNullOrWhiteSpace(uri))
            {
                return DefaultBitmap;
            }

            if (baseUri == null)
            {
                // 本地资源
                return BitmapSourceCache.GetOrAdd(uri, u => ConvertToBitmapSource((Bitmap)Image.FromFile(uri)));
            }
            else
            {
                // 远程资源
                uri = baseUri.TrimEnd('/') + "/" + uri;
                return BitmapSourceCache.GetOrAdd(uri, u => ConvertToBitmapSource(uri, UriKind.RelativeOrAbsolute));
            }
        }

        #endregion
    }
}
