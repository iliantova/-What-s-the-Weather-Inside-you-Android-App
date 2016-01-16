using PsyhosAndroidAppServer.Data.Models;
using PsyhosAndroidAppServer.Web.Api.Infrastructure.Mappings;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PsyhosAndroidAppServer.Web.Api.Models.Answers
{
    public class AnswerResponseModel : IMapFrom<Answer>
    {
        public string Text { get; set; }

        public int Value { get; set; }

    }
}