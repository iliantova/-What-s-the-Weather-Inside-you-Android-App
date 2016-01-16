namespace PsyhosAndroidAppServer.Web.Api.Models.Questions
{
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using Common.Constants;
    using Data.Models;
    using Infrastructure.Mappings;

    public class QuestionRequestModel : IMapFrom<Question>, IValidatableObject
    {
        [Required]
        [MinLength(TextConstants.TextMinLength)]
        [MaxLength(TextConstants.TextMaxLength)]
        public string Text { get; set; }

        // TODO add ienumerable or AnswersResponseModel

        public IEnumerable<ValidationResult> Validate(ValidationContext validationContext)
        {
            if (this.Text.Length < TextConstants.TextMinLength || this.Text.Length > TextConstants.TextMaxLength)
            {
                yield return new ValidationResult("Invalid question length!");
            }
        }
    }
}
