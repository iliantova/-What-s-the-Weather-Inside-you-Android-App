namespace PsyhosAndroidAppServer.Services.Data
{
    using System;
    using System.Linq;
    using Contracts;
    using PsyhosAndroidAppServer.Data.Models;
    using PsyhosAndroidAppServer.Data.Repositories;

    public class UsersService : IUsersService
    {
        private readonly IRepository<User> users;

        public UsersService(IRepository<User> users)
        {
            this.users = users;
            
        }

        public IQueryable<User> GetByUserName(string username)
        {
            return this.users
                .All()
                .Where(u => u.UserName == username);
        }

        //public void Rate(Rating rating)
        //{
        //    rating.CreatedOn = DateTime.UtcNow;
        //    this.ratings.Add(rating);
        //    this.ratings.SaveChanges();
        //}
    }
}
