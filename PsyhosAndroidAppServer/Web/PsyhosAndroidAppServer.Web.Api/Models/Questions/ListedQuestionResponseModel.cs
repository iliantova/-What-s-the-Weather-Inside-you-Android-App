namespace PsyhosAndroidAppServer.Web.Api.Models.Questions
{
    using AutoMapper;
    using Data.Models;
    using Infrastructure.Mappings;

    public class ListedQuestionResponseModel : IMapFrom<Question>
    {
        public int Id { get; set; }

        public string Text { get; set; }

        public virtual void CreateMappings(IConfiguration configuration)
        {
            //configuration.CreateMap<Question, RealEstateDetailsResponseModel>()
            //    .ForMember(m => m.RealEstateType, opts => opts.MapFrom(r => r.Type.ToString()));
        }
    }
}
