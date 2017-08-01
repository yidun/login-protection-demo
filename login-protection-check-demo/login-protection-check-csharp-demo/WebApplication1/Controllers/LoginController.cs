using LoginAntiCheatAPIDemo.Sdk;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace LoginAntiCheatAPIDemo.Controllers
{
    public class LoginController : Controller
    {
        private static string secretId = "YOUR_SECRET_ID"; // 密钥对id
        private static string secretKey = "YOUR_SECRET_KEY"; // 密钥对key
        private static string businessID = "YOUR_BUSINESS_ID"; // 密钥对key
        private LoginAntiCheatVerifier verifier = new LoginAntiCheatVerifier(new NESecretPair(secretId, secretKey), businessID);

        // GET: Login
        // 显示演示用的登录页面，这个是默认的首页，配置在 RouteConfig.cs 里
        public ActionResult Index()
        {
            return View();
        }

        // POST: Submit
        /// <summary>
        /// 演示用的提交登录请求接口
        /// </summary>
        /// <param name="username">表单用户名</param>
        /// <param name="password">表单密码</param>
        /// <param name="token">token</param>
        /// <returns></returns>
        [HttpPost]
        public ActionResult Submit(string username, string password, string token)
        {
            //add logic to verify username and password
            VerifyResultType verifyResult = verifier.verify(token);
            string msg;
            switch (verifyResult)
            {
                case VerifyResultType.Success:
                    msg = "正常";
                    break;
                case VerifyResultType.Suspicion:
                    msg = "有恶意登陆嫌疑";
                    break;
                case VerifyResultType.ConfirmedCheat:
                    msg = "明显的恶意登录";
                    break;
                default:
                    msg = "调用出错，可能是参数设置错误";
                    break;
            }

            return Content(msg);// 这里简单的在页面上显示一下结果即可
        }
    }
}