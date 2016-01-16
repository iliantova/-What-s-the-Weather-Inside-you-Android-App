namespace PsyhosAndroidAppServer.Data.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using Common.Constants;

    public class Question
    {
        private ICollection<Answer> answers;

        public Question()
        {
            this.answers = new HashSet<Answer>();
        }

        [Key]
        public int Id { get; set; }
      
        [Required]
        [MinLength(TextConstants.TextMinLength)]
        [MaxLength(TextConstants.TextMaxLength)]
        public string Text { get; set; }

        public virtual ICollection<Answer> Answers
        {
            get { return this.answers; }
            set { this.answers = value; }
        }
    }
}
