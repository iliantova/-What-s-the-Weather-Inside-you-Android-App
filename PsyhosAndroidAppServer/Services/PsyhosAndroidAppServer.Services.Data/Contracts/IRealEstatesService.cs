namespace PsyhosAndroidAppServer.Services.Data.Contracts
{
    using System.Linq;
    using PsyhosAndroidAppServer.Data.Models;

    public interface IQuestionService
    {
        IQueryable<Question> GetAll(int skip, int take);

        IQueryable<Question> GetById(int id);
        
        int AddNew(Question newRealEstate, string userId);
    }
}
