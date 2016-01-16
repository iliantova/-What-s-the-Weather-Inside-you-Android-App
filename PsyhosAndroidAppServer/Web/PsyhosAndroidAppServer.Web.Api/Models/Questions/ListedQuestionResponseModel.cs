namespace PsyhosAndroidAppServer.Web.Api.Models.Questions
{
    using Answers;
    using AutoMapper;
    using Data.Models;
    using System.Linq;
    using Infrastructure.Mappings;
    using System.Collections.Generic;
    public class ListedQuestionResponseModel : IMapFrom<Question>, IHaveCustomMappings
    {
        public int Id { get; set; }

        public string Text { get; set; }

         public IEnumerable<AnswerResponseModel> Answers { get; set; }

        public virtual void CreateMappings(IConfiguration configuration)
        {
            configuration.CreateMap<Question, ListedQuestionResponseModel>()
                .ForMember(r => r.Answers, opts => opts.MapFrom(r => r.Answers));
        }
    }
}
