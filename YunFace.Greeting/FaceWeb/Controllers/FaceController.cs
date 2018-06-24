using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using XJFaceWeb.Models;
using YunFace.Client;
using YunFace.Client.Models;

namespace XJFaceWeb.Controllers
{
    public class FaceController : Controller
    {
        // GET: Face
        public ActionResult FaceCollect()
        {
            string posturl = Helper.Posturl;
            ViewBag.posturl = posturl == "" ? "https://open.yunface.net:33366/Face/AddorUpdate" : posturl;
            return View();
        }
        public JsonResult AddorUpdate(IdentityEntity item, string face1)
        {
            try
            {
                var identityInfo = new IdentityInfo()
                {
                    Id = item.Id, //Api会根据Id>0做判断，新增or更新
                    Name = item.Name,
                    No = item.No,
                    IsActive = true,
                    Tel = item.Tel,
                    Sex = item.Sex,
                    EXT_4 = item.EXT_4,
                    EXT_0 = item.EXT_5,
                    EXT_6 = item.EXT_6,
                    Company = item.Company,
                }.ToString();

                List<string> errorList = new List<string>();

                string imgUrlName = "";
                //获取base64字符串          
                byte[] imgBytes = Convert.FromBase64String(face1);
                //将base64字符串转换为字节数组            
                System.IO.Stream stream = new System.IO.MemoryStream(imgBytes);
                //将字节数组转换为字节流      
                //将流转回Image，用于将PNG式照片转为jpg，压缩体积以便保存。         
                System.Drawing.Image imge = System.Drawing.Image.FromStream(stream);
                imgUrlName = DateTime.Now.ToString("yyyyMMddHHmmssfff") + ".jpg";
                AppHelper.EnsureDirectory(AppHelper.AppTempPath);
                imge.Save(Path.Combine(AppHelper.AppTempPath, imgUrlName));

                //构造 PutIntoFaceLibrary Request
                JsonRequest request = new JsonRequest()
                {
                    Action = JsonRequestAction.PutFaceIntoLibrary,
                    Args = new string[] {
                            Helper.WebClient_AppKey,
                            Helper.DeviceId,
                            identityInfo,
                            face1
                            }
                };
                var responseTemp = AppHelper.FaceApi(request);
                if (responseTemp.Result)
                {
                }
                else
                {
                    errorList.Add(responseTemp.Message);
                }
                if (errorList.Count > 0)
                {
                    return new JsonResult
                    {
                        Data = new { success = false, msg = string.Format("登记失败，错误信息：{0}", JsonConvert.SerializeObject(errorList)) },
                    };
                }
                else
                {
                    return new JsonResult
                    {
                        Data = new { success = true, msg = "登记成功" },
                    };
                }
            }
            catch (Exception ex)
            {
                return new JsonResult
                {
                    Data = new
                    {
                        success = false,
                        msg = ex.Message
                    }
                };
            }
        }

    }
}