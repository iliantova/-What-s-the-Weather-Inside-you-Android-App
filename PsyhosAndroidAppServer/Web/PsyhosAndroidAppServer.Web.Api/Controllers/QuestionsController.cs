namespace PsyhosAndroidAppServer.Web.Api.Controllers
{
    using System.Collections;
    using System.Linq;
    using System.Web.Http;
    using AutoMapper;
    using Common.Constants;
    using Infrastructure.Validation;
    using Services.Data.Contracts;
    using AutoMapper.QueryableExtensions;
    using Data.Models;
    using Microsoft.AspNet.Identity;
    using Models.Questions;

    public class QuestionsController : ApiController
    {
        private readonly IQuestionService questions;

        public QuestionsController(IQuestionService questions)
        {
            this.questions = questions;
        }

        [ValidateTake]
        public IHttpActionResult Get()
        {
            var result = this.questions
                .GetAll()
                .ProjectTo<ListedQuestionResponseModel>()
                .ToList();

            return this.Ok(result);
        }

        public IHttpActionResult Get(int id)
        {
            var query = this.questions.GetTenRandomQuestions();
            ListedQuestionResponseModel result;
            if (this.User.Identity.IsAuthenticated)
            {
                result = query
                    .ProjectTo<ListedQuestionResponseModel>()
                    .FirstOrDefault();
            }
            else
            {
                result = query
                    .ProjectTo<ListedQuestionResponseModel>()
                    .FirstOrDefault();
            }

            return this.Ok(result);
        }

        [Authorize]
        [ValidateModel]
        public IHttpActionResult Post(QuestionRequestModel model)
        {
            var newQuestions = Mapper.Map<Question>(model);
            var id = this.questions.AddNew(newQuestions, this.User.Identity.GetUserId());

            var result = this.questions
                .GetById(id)
                .ProjectTo<ListedQuestionResponseModel>()
                .FirstOrDefault();

            return this.Created($"/api/Questions/{id}", result);
        }
    }
}
