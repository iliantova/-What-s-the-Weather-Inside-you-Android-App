using AutoMapper;
using AutoMapper.QueryableExtensions;
using Microsoft.AspNet.Identity;
using PsyhosAndroidAppServer.Data.Models;
using PsyhosAndroidAppServer.Services.Data.Contracts;
using PsyhosAndroidAppServer.Web.Api.Infrastructure.Validation;
using PsyhosAndroidAppServer.Web.Api.Models.Answers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;

namespace PsyhosAndroidAppServer.Web.Api.Controllers
{
    public class AnswersController : ApiController
    {
        private readonly IAnswerService answers;

        public AnswersController(IAnswerService answers)
        {
            this.answers = answers;
        }

        [ValidateTake]
        public IHttpActionResult Get(int questionId, int take = 4)
        {
            var result = this.answers
                .GetAllByQuestionId(questionId, take);

            return this.Ok(result);
        }

        public IHttpActionResult Get(int id)
        {
            var query = this.answers.GetById(id);
            AnswerResponseModel result;
            if (this.User.Identity.IsAuthenticated)
            {
                result = query
                    .ProjectTo<AnswerResponseModel>()
                    .FirstOrDefault();
            }
            else
            {
                result = query
                    .ProjectTo<AnswerResponseModel>()
                    .FirstOrDefault();
            }

            return this.Ok(result);
        }

        // [Authorize]
        // [ValidateModel]
        public IHttpActionResult Post(AnswerResponseModel model)
        {
            var newAnswer = Mapper.Map<Answer>(model);
            var id = this.answers.AddNew(newAnswer, this.User.Identity.GetUserId());

            var result = this.answers
                .GetById(id)
                .ProjectTo<AnswerResponseModel>()
                .FirstOrDefault();

            return this.Created($"/api/Answers/{id}", result);
        }

    }
}