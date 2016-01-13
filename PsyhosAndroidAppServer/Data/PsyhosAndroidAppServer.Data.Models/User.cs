namespace PsyhosAndroidAppServer.Data.Models
{
    using Common.Constants;
    using Microsoft.AspNet.Identity;
    using Microsoft.AspNet.Identity.EntityFramework;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.Security.Claims;
    using System.Threading.Tasks;

    public class User : IdentityUser
    {
        private ICollection<FeelingsPicture> feelingsPicture;

        
        [MinLength(UserConstants.NameMinLength)]
        [MaxLength(UserConstants.NameMaxLength)]
        public string FirstName { get; set; }

        
        [MinLength(UserConstants.NameMinLength)]
        [MaxLength(UserConstants.NameMaxLength)]
        public string LastName { get; set; }
        public User()
        {
            this.feelingsPicture = new HashSet<FeelingsPicture>();            
        }
        
        public virtual ICollection<FeelingsPicture> FeelingsPicture
        {
            get { return this.feelingsPicture; }
            set { this.feelingsPicture = value; }
        }

        public async Task<ClaimsIdentity> GenerateUserIdentityAsync(UserManager<User> manager, string authenticationType)
        {
            var userIdentity = await manager.CreateIdentityAsync(this, authenticationType);
            return userIdentity;
        }
    }
}
