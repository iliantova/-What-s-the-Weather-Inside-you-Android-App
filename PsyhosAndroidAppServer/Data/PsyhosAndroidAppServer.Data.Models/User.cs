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
        private ICollection<Answer> answers;
        private ICollection<Question> questions;
        private ICollection<Result> results;
        private ICollection<Advice> advices;
        private ICollection<FeelingsPicture> feellingPictures;
        private ICollection<Thought> thougths;

        [MinLength(UserConstants.NameMinLength)]
        [MaxLength(UserConstants.NameMaxLength)]
        public string FirstName { get; set; }
      
        [MinLength(UserConstants.NameMinLength)]
        [MaxLength(UserConstants.NameMaxLength)]
        public string LastName { get; set; }
        public User()
        {
            this.answers = new HashSet<Answer>();
            this.questions = new HashSet<Question>();
            this.results = new List<Result>();
            this.advices = new List<Advice>();
        }
        
        public virtual ICollection<Answer> Answers
        {
            get { return this.answers; }
            set { this.answers = value; }
        }

        public virtual ICollection<Question> Questions
        {
            get { return this.questions; }
            set { this.questions = value; }
        }

        public virtual ICollection<Result> Results
        {
            get { return this.results; }
            set { this.results = value; }
        }

        public virtual ICollection<Advice> Advices
        {
            get { return this.advices; }
            set { this.advices = value; }
        }

        public virtual ICollection<FeelingsPicture> FeellingPictures
        {
            get { return this.feellingPictures; }
            set { this.feellingPictures = value; }
        }

        public virtual ICollection<Thought> Thoughts
        {
            get { return this.thougths; }
            set { this.thougths = value; }
        }

        public async Task<ClaimsIdentity> GenerateUserIdentityAsync(UserManager<User> manager, string authenticationType)
        {
            var userIdentity = await manager.CreateIdentityAsync(this, authenticationType);
            return userIdentity;
        }
    }
}
