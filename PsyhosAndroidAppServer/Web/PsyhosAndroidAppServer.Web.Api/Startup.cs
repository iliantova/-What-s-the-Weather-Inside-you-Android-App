﻿using Microsoft.Owin;
using Owin;
using System.Web.Http;

using Ninject.Web.Common.OwinHost;
using Ninject.Web.WebApi.OwinHost;
using PsyhosAndroidAppServer.Common.Constants;

[assembly: OwinStartup(typeof(PsyhosAndroidAppServer.Web.Api.Startup))]

namespace PsyhosAndroidAppServer.Web.Api
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            AutoMapperConfig.RegisterMappings(Assemblies.WebApi);

            DatabaseConfig.Initialize();

            ConfigureAuth(app);

            var httpConfig = new HttpConfiguration();
            
            WebApiConfig.Register(httpConfig);

            httpConfig.EnsureInitialized();

            app
                .UseNinjectMiddleware(NinjectConfig.CreateKernel)
                .UseNinjectWebApi(httpConfig);
        }
    }
}
