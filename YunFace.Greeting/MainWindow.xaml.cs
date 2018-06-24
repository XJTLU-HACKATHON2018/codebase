using log4net;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Threading;
using YunFace.Client;
using YunFace.Client.Capturing;
using YunFace.Client.Models;
using YunFace.Greeting.Models;

namespace YunFace.Greeting
{
    public partial class MainWindow : Window
    {
        private static readonly object _mutex = new object();
        private ILog _log = LoggerManager.Instance.RootLogger;

        private DispatcherTimer _timeTimer;
        private DispatcherTimer _adTimer;
        private Uri[] _adUris;
        private int _adIndex;

        private IFaceCapture _faceCapture;
        private Storyboard _shadowSliding;
        private DispatcherTimer _shadowSlidingTimer;
        private List<IdentifyingHistory> _shadowSlidingData;
        private List<IdentifyingHistory> _identifyingHistoryData;
        private volatile string _greetingWords;

        #region 初始化

        public MainWindow()
        {
            InitializeComponent();
            Initialize();
        }
        private void logoBox_MouseDown(object sender, MouseButtonEventArgs e)
        {
            if (e.ClickCount == 3)
            {
                this.Close();
            }
        }
        private void Window_Closing(object sender, CancelEventArgs e)
        {
            StopFaceCapture();
        }
        private void TimeTimer_Tick(object sender, EventArgs e)
        {
            currentTime.Text = DateTime.Now.ToString(AppHelper.DateTimeFormat);
        }
        private void AdTimer_Tick(object sender, EventArgs e)
        {
            if (_adUris == null || _adUris.Length <= 0)
            {
                return;
            }

            if (_adIndex >= _adUris.Length)
            {
                _adIndex = 0;
            }

            var newSource = _adUris[_adIndex++];
            if (adsBox.Source == null || !adsBox.Source.Equals(newSource))
            {
                adsBox.Source = newSource;
            }
        }
        private void adsBox_MediaEnded(object sender, RoutedEventArgs e)
        {
            adsBox.Position = TimeSpan.Zero;
        }

        private void Initialize()
        {
#if !DEBUG
            this.Topmost = true;
#endif
            Title = string.Format("{0} V{1}", AppHelper.AppId, AppHelper.AppVersion);
            videoBox.Opacity = AppHelper.VideoBoxOpacity;
            _greetingWords = AppHelper.DefaultGreetingWords;
            identifyingHistories.Items.Clear();
            if (AppHelper.IdentifyingHistoryCount > 0)
            {
                identifyingHistories.Visibility = Visibility.Visible;
            }
            else
            {
                identifyingHistories.Visibility = Visibility.Hidden;
            }

            _timeTimer = new DispatcherTimer();
            _timeTimer.Interval = TimeSpan.FromSeconds(1);
            _timeTimer.Tick += TimeTimer_Tick;
            _timeTimer.Start();

            _identifyingHistoryData = new List<IdentifyingHistory>();
            _shadowSlidingData = new List<IdentifyingHistory>();
            _shadowSliding = (Storyboard)FindResource("ShadowSliding");
            _shadowSliding.Completed += ShadowSliding_Completed;

            _shadowSlidingTimer = new DispatcherTimer();
            _shadowSlidingTimer.Interval = TimeSpan.FromMilliseconds(500);
            _shadowSlidingTimer.Tick += ShadowSlidingTimer_Tick;
            _shadowSlidingTimer.Start();

            if (AppHelper.AdFiles.Length > 0)
            {
                adsBox.Visibility = Visibility.Visible;
                adsBox.Volume = AppHelper.AdsVolume;

                _adUris = AppHelper.AdFiles.Select(f => new Uri(f, UriKind.Absolute)).ToArray();
                _adIndex = 0;

                _adTimer = new DispatcherTimer();
                _adTimer.Interval = TimeSpan.FromMilliseconds(AppHelper.AdsInterval);
                _adTimer.Tick += AdTimer_Tick;
                _adTimer.Start();
            }
            else
            {
                adsBox.Visibility = Visibility.Hidden;
            }

            StartFaceCapture();
        }

        private void FaceCapture_Error(object sender, RunWorkerCompletedEventArgs e)
        {
            _log.ErrorFormat("捕捉器 [{0}] 错误: {1}", e.Result, e.Error);
        }

        #endregion

        private void StartFaceCapture()
        {
            StopFaceCapture();
            lock (_mutex)
            {
                try
                {
                    int cameraId;
                    if (int.TryParse(AppHelper.CaptureSource, out cameraId))
                    {
                        _faceCapture = AppHelper.CreateFaceCapture(CaptureSourceType.Camera, cameraId);
                    }
                    else if (File.Exists(AppHelper.CaptureSource))
                    {
                        _faceCapture = AppHelper.CreateFaceCapture(CaptureSourceType.ImageFile, AppHelper.CaptureSource);
                    }
                    else if (AppHelper.CaptureSource.StartsWith("rtsp://"))
                    {
                        _faceCapture = AppHelper.CreateFaceCapture(CaptureSourceType.VideoRtsp, AppHelper.CaptureSource);
                    }
                    else
                    {
                        _faceCapture = AppHelper.CreateFaceCapture(CaptureSourceType.ImageUrl, AppHelper.CaptureSource);
                    }

                    _faceCapture.FramesPerCapture = AppHelper.FramesPerCapture;
                    _faceCapture.FrameInterval = AppHelper.FrameInterval;
                    _faceCapture.CroppingRectangle = AppHelper.CroppingRectangle;

                    _faceCapture.BorderDetectContourAreaThreshold = AppHelper.BorderDetectContourAreaThreshold;
                    _faceCapture.BorderDetectDelayerCount = AppHelper.BorderDetectDelayerCount;
                    _faceCapture.FaceDetectMinNeighbors = AppHelper.FaceDetectMinNeighbors;
                    _faceCapture.FaceDetectMinSize = AppHelper.FaceDetectMinSize;
                    _faceCapture.OutlineThickness = string.IsNullOrWhiteSpace(AppHelper.FaceValidators)
                        ? AppHelper.OutlineThickness
                        : 0;
                    _faceCapture.OutlineText = AppHelper.DefaultTag;

                    _faceCapture.Error += FaceCapture_Error;
                    _faceCapture.Captured = (faceCapture, e) =>
                    {
                        #region 在前端界面中预览捕获帧

                        try
                        {
                            videoBox.Source = AppHelper.ConvertToBitmapSource(e.Frame);
                        }
                        catch { }
                        try
                        {
                            greetingWords.Text = _greetingWords;
                        }
                        catch { }

                        #endregion
                    };
                    _faceCapture.Resolving = (faceCapture, e) =>
                    {
                        #region 对后台队列执行身份识别

                        if (e == null || e.Frame == null || e.Frame.Bitmap == null)
                        {
                            return;
                        }

                        try
                        {
                            #region Face API

                            var response = AppHelper.FaceApi(new JsonRequest()
                            {
                                Action = JsonRequestAction.MatchIdentityByFace,
                                Args = new string[]
                                {
                                    AppHelper.AppKey,
                                    AppHelper.CompressAsBase64(e.Frame.Bitmap, AppHelper.ZoomSize, AppHelper.JpegQuality),
                                    AppHelper.FaceValidators
                                }
                            });
                            if (response.Result)
                            {
                                var identityInfos = IdentityInfo.LoadArray(response.Message);
                                if (identityInfos != null)
                                {
                                    lock (_mutex)
                                    {
                                        var matchingIdentityInfos = new List<IdentityInfo>();
                                        for (var i = 0; i < Math.Min(identityInfos.Length, AppHelper.MatchingFaceCount); i++)
                                        {
                                            if (identityInfos[i] != null && identityInfos[i].Length > 0)
                                            {
                                                var identityInfo = identityInfos[i][0];
                                                matchingIdentityInfos.Add(identityInfo);

                                                #region 进入特效队列

                                                if (_shadowSlidingData.Count < AppHelper.PlayingQueueLength)
                                                {
                                                    var identifyingHistory = new IdentifyingHistory(identityInfo);
                                                    if (!_shadowSlidingData.Any(h => h.Identity.Hash == identifyingHistory.Identity.Hash))
                                                    {
                                                        _shadowSlidingData.Insert(0, identifyingHistory);
                                                    }
                                                }

                                                #endregion
                                            }
                                        }

                                        e.State.IdentityInfos = matchingIdentityInfos;

                                        #region 欢迎词

                                        var firstMatchingIdentityInfo = matchingIdentityInfos.FirstOrDefault();
                                        if (firstMatchingIdentityInfo != null)
                                        {
                                            if (!string.IsNullOrWhiteSpace(firstMatchingIdentityInfo.Remark))
                                            {
                                                _greetingWords = firstMatchingIdentityInfo.Remark;
                                            }
                                            else
                                            {
                                                _greetingWords = AppHelper.DefaultGreetingWords;
                                            }
                                        }

                                        #endregion
                                    }
                                }
                                else
                                {
                                    _log.WarnFormat("后台执行身份识别失败: 无法解析身份信息");
                                }
                            }
                            else
                            {
                                #region 欢迎词

                                if (!string.IsNullOrWhiteSpace(AppHelper.FaceValidators)
                                    && response.Message.IndexOf(AppHelper.FaceValidators, StringComparison.OrdinalIgnoreCase) >= 0)
                                {
                                    _greetingWords = "注意：检测到伪造身份攻击！";
                                }
                                else
                                {
                                    _greetingWords = AppHelper.DefaultGreetingWords;
                                }

                                #endregion

                                _log.WarnFormat("后台执行身份识别失败: {0}", response.Message);
                            }

                            #endregion
                        }
                        catch (Exception error)
                        {
                            _log.ErrorFormat("后台执行身份识别失败: {0}", error);
                        }

                        #endregion
                    };
                    _faceCapture.Resolved = (faceCapture, e) =>
                    {
                        if (e == null || e.State == null)
                        {
                            return;
                        }
                        var identityInfos = e.State.IdentityInfos as List<IdentityInfo>;
                        if (identityInfos == null || identityInfos.Count == 0)
                        {
                            return;
                        }

                        foreach (var identityInfo in identityInfos)
                        {
                            #region 额外指令

                            identityInfo.EXT_3 = AppHelper.DeviceId.ToString(); // 追加设备号 EXT_3
                            for (var i = 0; i < AppHelper.CommandOnAuthenticated.Length; i++)
                            {
                                var commandOnAuthenticated = AppHelper.CommandOnAuthenticated[i];
                                if (string.IsNullOrWhiteSpace(commandOnAuthenticated))
                                {
                                    continue;
                                }
                                var commandArgsOnAuthenticated = AppHelper.CommandArgsOnAuthenticated.Length > i
                                    ? string.Format(AppHelper.CommandArgsOnAuthenticated[i], identityInfo) // 代入身份
                                    : string.Empty;
                                if (AppHelper.CommandArgsEscaping.Length == 2)
                                {
                                    commandArgsOnAuthenticated = commandArgsOnAuthenticated.Replace(
                                        AppHelper.CommandArgsEscaping[0],
                                        AppHelper.CommandArgsEscaping[1]);
                                }
                                try
                                {
                                    var process = new Process();
                                    process.StartInfo.UseShellExecute = AppHelper.CommandByShell;
                                    process.StartInfo.FileName = commandOnAuthenticated;
                                    process.StartInfo.Arguments = commandArgsOnAuthenticated;
                                    process.StartInfo.WorkingDirectory = System.IO.Path.IsPathRooted(commandOnAuthenticated)
                                        ? System.IO.Path.GetDirectoryName(commandOnAuthenticated)
                                        : AppHelper.AppPath;
                                    if (!AppHelper.CommandWithWindow)
                                    {
                                        process.StartInfo.WindowStyle = ProcessWindowStyle.Hidden;
                                    }
                                    process.Start();
                                }
                                catch (Exception error)
                                {
                                    _log.ErrorFormat("执行额外指令 [{0} {1}] 失败: {2}",
                                        commandOnAuthenticated,
                                        commandArgsOnAuthenticated,
                                        error);
                                }
                            }

                            #endregion
                        }
                    };

                    _faceCapture.Start();
                }
                catch (Exception error)
                {
                    _log.ErrorFormat("启动捕捉器 [{0}] 失败: {1}", AppHelper.CaptureSource, error);
                }
            }
        }
        private void StopFaceCapture()
        {
            lock (_mutex)
            {
                if (_faceCapture != null)
                {
                    try
                    {
                        _faceCapture.Dispose();
                    }
                    catch { }
                    finally
                    {
                        _faceCapture = null;
                    }
                }
            }
        }

        private void ShadowSlidingTimer_Tick(object sender, EventArgs e)
        {
            _shadowSlidingTimer.Stop();

            IdentifyingHistory identifyingHistory;
            lock (_mutex)
            {
                if (_shadowSlidingData.Count == 0)
                {
                    _shadowSlidingTimer.Start();
                    return;
                }

                identifyingHistory = _shadowSlidingData.Last();
                _shadowSlidingData.RemoveAt(_shadowSlidingData.Count - 1);

                #region 进入识别历史列表

                _identifyingHistoryData.RemoveAll(h => h.Identity.Hash == identifyingHistory.Identity.Hash);
                _identifyingHistoryData.Insert(0, identifyingHistory);
                if (_identifyingHistoryData.Count > AppHelper.IdentifyingHistoryCount)
                {
                    _identifyingHistoryData.RemoveAt(_identifyingHistoryData.Count - 1);
                }

                #endregion
            }

            try
            {
                identityCardShadow.Initialize(identifyingHistory);
                _shadowSliding.Stop();
                _shadowSliding.Begin();
            }
            catch (Exception error)
            {
                _log.WarnFormat("播放特效失败: {0}", error);
                _shadowSliding.Stop();
                _shadowSlidingTimer.Start();
            }
        }
        private void ShadowSliding_Completed(object sender, EventArgs e)
        {
            try
            {
                identifyingHistories.Items.Clear();

                #region 刷新识别历史

                IdentifyingHistory[] identifyingHistoryData;
                lock (_mutex)
                {
                    identifyingHistoryData = _identifyingHistoryData.ToArray();
                }
                if (identifyingHistoryData.Length > 0)
                {
                    foreach (var identifyingHistory in identifyingHistoryData)
                    {
                        var identityCard = new IdentityCard();
                        identityCard.Initialize(identifyingHistory);
                        identifyingHistories.Items.Add(identityCard);
                    }
                }

                #endregion
            }
            catch (Exception error)
            {
                _log.WarnFormat("刷新识别历史失败: {0}", error);
            }
            finally
            {
                _shadowSlidingTimer.Start();
            }
        }
    }
}
