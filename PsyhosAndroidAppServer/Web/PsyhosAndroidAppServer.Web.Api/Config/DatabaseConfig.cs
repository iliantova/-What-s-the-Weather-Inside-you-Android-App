namespace PsyhosAndroidAppServer.Web.Api
{
    using PsyhosAndroidAppServer.Data;
    using PsyhosAndroidAppServer.Data.Migrations;
    using System.Data.Entity;

    public static class DatabaseConfig
    {
        public static void Initialize()
        {
            Database.SetInitializer(new MigrateDatabaseToLatestVersion<PsyhosAndroidAppServerDbContext, Configuration>());
        }
    }
}
