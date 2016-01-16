using PsyhosAndroidAppServer.Common.Constants;
using PsyhosAndroidAppServer.Data.Models;
using PsyhosAndroidAppServer.Web.Api.Infrastructure.Mappings;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace PsyhosAndroidAppServer.Web.Api.Models.Answers
{
    public class AnswerRequestModel : IMapFrom<Answer>, IValidatableObject
    {
        [Required]
        [MinLength(TextConstants.TextMinLength)]
        [MaxLength(TextConstants.TextMaxLength)]
        public string Text { get; set; }

        public int QuestionId { get; set; }

        public IEnumerable<ValidationResult> Validate(ValidationContext validationContext)
        {
            if (this.Text.Length < TextConstants.TextMinLength || this.Text.Length > TextConstants.TextMaxLength)
            {
                yield return new ValidationResult("Invalid answer length!");
            }
        }
    }
}