namespace PsyhosAndroidAppServer.Web.Api.Models.Comments
{
    using System;
    using AutoMapper;
    using Data.Models;
    using Infrastructure.Mappings;

    public class CommentResponseModel : IMapFrom<Result>, IHaveCustomMappings
    {
        public string Content { get; set; }

        public string UserName { get; set; }

        public DateTime CreatedOn { get; set; }

        public void CreateMappings(IConfiguration configuration)
        {
            configuration.CreateMap<Result, CommentResponseModel>()
                .ForMember(c => c.UserName, opts => opts.MapFrom(c => c.Id));
        }
    }
}
