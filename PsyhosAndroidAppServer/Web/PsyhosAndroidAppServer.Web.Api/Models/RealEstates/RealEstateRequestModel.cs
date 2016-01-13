namespace PsyhosAndroidAppServer.Web.Api.Models.RealEstates
{
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using Common.Constants;
    using Data.Models;
    using Infrastructure.Mappings;

    public class RealEstateRequestModel : IMapFrom<Question>, IValidatableObject
    {
        [Required]
        [MinLength(1)]
        [MaxLength(1)]
        public string Title { get; set; }

        [Required]
        [MinLength(1)]
        [MaxLength(1)]
        public string Description { get; set; }

        [Required]
        public string Address { get; set; }

        public string Contact { get; set; }

        public decimal? SellingPrice { get; set; }

        public decimal? RentingPrice { get; set; }

        public IEnumerable<ValidationResult> Validate(ValidationContext validationContext)
        {
            if (!this.SellingPrice.HasValue && !this.RentingPrice.HasValue)
            {
                yield return new ValidationResult("Real estate must be marked as available for selling or available for renting!");
            }
        }
    }
}
