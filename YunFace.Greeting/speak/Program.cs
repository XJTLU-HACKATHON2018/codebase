using System;
using System.Collections.Generic;
using System.Linq;
using System.Speech.Synthesis;
using System.Text;
using System.Threading;

namespace speak
{
    class Program
    {
        private static Mutex _appMutex;

        public static void Log(string format, params object[] args)
        {
            var message = format == null
                ? string.Empty
                : DateTime.Now.ToString("[yyyy-MM-dd HH:mm:ss] ") + (args.Length == 0 ? format : string.Format(format, args));
            message += Environment.NewLine;
            Console.Write(message);
        }

        /// <summary>
        /// Text, [Volume]
        /// </summary>
        static void Main(string[] args)
        {
            const string AppId = "语音播报程序";
            string AppVersion = System.Reflection.Assembly.GetExecutingAssembly().GetName().Version.ToString();

            Log("{0} V{1}", AppId, AppVersion);

            #region 参数

            var arg = args.AsEnumerable().GetEnumerator();
            string text;
            if (!arg.MoveNext())
            {
                Log("Text is required.");
                return;
            }
            text = arg.Current;

            int volume;
            if (!arg.MoveNext() || !int.TryParse(arg.Current, out volume) || volume < 0 || volume > 100)
            {
                volume = 100;
            }

            #endregion
            #region 重复启动

            var appName = AppId;
            bool allowStart;
            _appMutex = new Mutex(true, appName, out allowStart); //单实例互斥
            if (!allowStart)
            {
                Log("Another instance is running.");
                return;
            }

            #endregion

            try
            {
                using (var speaker = new SpeechSynthesizer())
                {
                    speaker.Volume = volume;
                    speaker.Speak(text);
                }
            }
            catch (Exception error)
            {
                Log("Failed to speak '{0}' with volume '{1}'. {2}", text, volume, error);
            }
        }
    }
}
