namespace PsyhosAndroidAppServer.Services.Data.Contracts
{
    using System.Linq;
    using PsyhosAndroidAppServer.Data.Models;

    public interface IQuestionService
    {
        IQueryable<Question> GetAll();

        IQueryable<Question> GetTenRandomQuestions();

        IQueryable<Question> GetById(int id);

        int AddNew(Question question, string userId);
    }
}
