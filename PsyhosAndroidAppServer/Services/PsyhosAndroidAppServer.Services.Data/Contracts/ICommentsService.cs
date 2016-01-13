namespace PsyhosAndroidAppServer.Services.Data.Contracts
{
    using System.Linq;
    using PsyhosAndroidAppServer.Data.Models;

    public interface IResultService
    {
        IQueryable<Result> GetAllByRealEstate(int realEstateId, int skip, int take);

        IQueryable<Result> GetById(int id);

        IQueryable<Result> GetAllByUser(string username, int skip, int take);

        int AddNew(Result comment, string userId);
    }
}
