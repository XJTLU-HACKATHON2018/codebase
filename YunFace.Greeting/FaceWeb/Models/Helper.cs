using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web;

namespace XJFaceWeb.Models
{
    public class Helper
    {
        public static readonly string WebClient_AppKey = ConfigurationManager.AppSettings["WebClient.AppKey"];
        public static readonly string FaceApiUrl = ConfigurationManager.AppSettings["FaceApiUrl"];
        public static readonly string Posturl = ConfigurationManager.AppSettings["Posturl"];
        public static readonly string DeviceId = ConfigurationManager.AppSettings["DeviceId"];

    }
}