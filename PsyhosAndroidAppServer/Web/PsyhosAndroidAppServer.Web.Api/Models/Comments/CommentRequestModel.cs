namespace PsyhosAndroidAppServer.Web.Api.Models.Comments
{
    using System.ComponentModel.DataAnnotations;
    using Common.Constants;
    using Data.Models;
    using Infrastructure.Mappings;

    public class CommentRequestModel: IMapFrom<Question>
    {
        public int RealEstateId { get; set; }
        
        [Required]
        [MinLength(1)]
        [MaxLength(2)]
        public string Content { get; set; }
    }
}
