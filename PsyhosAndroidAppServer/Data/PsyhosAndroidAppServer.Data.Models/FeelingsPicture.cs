namespace PsyhosAndroidAppServer.Data.Models
{
    using System;
    using System.ComponentModel.DataAnnotations;
    using Common.Constants;

    public class FeelingsPicture
    {
        [Key]
        public int Id { get; set; }

        [Required]
        [MinLength(TextConstants.TextMinLength)]
        [MaxLength(TextConstants.TextMaxLength)]
        public string Text { get; set; }

        public DateTime CreatedOn { get; set; }

        public string Pictures { get; set; }

        public int ResultId { get; set; }

        public virtual Result Result { get; set; }

        public int ThoughtId { get; set; }

        public virtual Thought Thought { get; set; }

        public string UserId { get; set; }

        public virtual User User { get; set; }
    }
}
