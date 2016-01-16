namespace PsyhosAndroidAppServer.Data.Models
{
    using System;
    using System.ComponentModel.DataAnnotations;
    using Common.Constants;

    public class Result
    {
        [Key]
        public int Id { get; set; }

        public int Value { get; set; }

        [Required]
        [MinLength(TextConstants.TextMinLength)]
        [MaxLength(TextConstants.TextMaxLength)]
        public string Text { get; set; }

        public string UserId { get; set; }

        public virtual User User { get; set; }

        [Required]
        public FeelingType feelingType { get; set; }
    }
}
