namespace PsyhosAndroidAppServer.Services.Data.Contracts
{
    using System.Linq;
    using PsyhosAndroidAppServer.Data.Models;

    public interface IUsersService
    {
        IQueryable<User> GetByUserName(string username);

        void Rate(Rating rating);
    }
}
