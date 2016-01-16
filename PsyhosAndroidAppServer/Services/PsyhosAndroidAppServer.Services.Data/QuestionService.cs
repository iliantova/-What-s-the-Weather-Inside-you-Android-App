namespace PsyhosAndroidAppServer.Services.Data
{
    using System;
    using System.Linq;
    using Contracts;
    using PsyhosAndroidAppServer.Data.Models;
    using PsyhosAndroidAppServer.Data.Repositories;

    public class QuestionService : IQuestionService
    {
        private readonly IRepository<Question> questions;

        public QuestionService(IRepository<Question> questions)
        {
            this.questions = questions;
        }
        
        public IQueryable<Question> GetAll(int skip = 0, int take = 0)
        {
            return this.questions
                .All()
                .OrderByDescending(r => r.Id)
                .Skip(skip)
                .Take(take);
        }

        public IQueryable<Question> GetById(int id)
        {
            return this.questions
                .All()
                .Where(r => r.Id == id);
        }

        public IQueryable<Question> GetTenRandomQuestions()
        {
            return this.questions
                .All()
                .OrderBy(x => Guid.NewGuid())
                .Take(10);
        }

        public int AddNew(Question question, string userId)
        {
            //newRealEstate.t = DateTime.UtcNow;
            //newRealEstate.UserId = userId;

            this.questions.Add(question);
            this.questions.SaveChanges();

            return question.Id;
        }
    }
}
