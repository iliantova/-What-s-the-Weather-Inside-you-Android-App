namespace PsyhosAndroidAppServer.Data
{
    using System.Data.Entity;
    using Microsoft.AspNet.Identity.EntityFramework;
    using Models;

    public class PsyhosAndroidAppServerDbContext : IdentityDbContext<User>, IPsyhosAndroidAppServerDbContext
    {
        public PsyhosAndroidAppServerDbContext()
            : base("DefaultConnection", throwIfV1Schema: false)
        {
        }
        
        public static PsyhosAndroidAppServerDbContext Create()
        {
            return new PsyhosAndroidAppServerDbContext();
        }
    }
}
