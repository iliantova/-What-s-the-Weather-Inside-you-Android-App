namespace PsyhosAndroidAppServer.Services.Data
{
    using PsyhosAndroidAppServer.Services.Data.Contracts;
    using System;
    using System.Linq;
    using PsyhosAndroidAppServer.Data.Models;
    using PsyhosAndroidAppServer.Data.Repositories;

    public class AnswerService : IAnswerService
    {
        private readonly IRepository<Answer> answers;

        public AnswerService(IRepository<Answer> answers)
        {
            this.answers = answers;
        }

        public int AddNew(Answer answer, string userId)
        {
            this.answers.Add(answer);
            this.answers.SaveChanges();

            return answer.Id;
        }

        public IQueryable<Answer> GetAllByQuestionId(int questionId, int take = 4)
        {
            return this.answers
                    .All()
                    .OrderBy(x => x.Id)
                    .Skip(questionId * take)
                    .Take(take);
        }

        public IQueryable<Answer> GetById(int id)
        {
            return this.answers
                    .All()
                    .Where(r => r.Id == id);
        }

        public IQueryable<Answer> GetFourSelectedAnswers()
        {
            throw new NotImplementedException();
        }
    }
}
