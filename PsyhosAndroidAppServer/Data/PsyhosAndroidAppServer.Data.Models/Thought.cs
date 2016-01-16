namespace PsyhosAndroidAppServer.Data.Models
{
    using Common.Constants;
    using System.ComponentModel.DataAnnotations;
    public class Thought
    {
        [Key]
        public int Id { get; set; }

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
