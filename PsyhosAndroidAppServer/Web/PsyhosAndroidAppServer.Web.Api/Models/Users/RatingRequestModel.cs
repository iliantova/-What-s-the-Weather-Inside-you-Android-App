namespace PsyhosAndroidAppServer.Web.Api.Models.Users
{
    using System.ComponentModel.DataAnnotations;
    using Common.Constants;
    using Data.Models;
    using Infrastructure.Mappings;

    public class RatingRequestModel : IMapFrom<Result>
    {
        [Required]
        public string UserId { get; set; }

        [Range(1, 2)]
        public int Value { get; set; }
    }
}
