using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(XJFaceWeb.Startup))]
namespace XJFaceWeb
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
