using PsyhosAndroidAppServer.Common.Constants;
using System.ComponentModel.DataAnnotations;

namespace PsyhosAndroidAppServer.Data.Models
{
   public class Answer
    {
        [Key]
        public int Id { get; set; }

        
       public int QuestionId { get; set; }
       

        public virtual Question Question { get; set; }

        public int Value { get; set; }

        [Required]
        [MinLength(TextConstants.TextMinLength)]
        [MaxLength(TextConstants.TextMaxLength)]
        public string Text { get; set; }

        public string UserId { get; set; }

        public virtual User User { get; set; }
    }
}
