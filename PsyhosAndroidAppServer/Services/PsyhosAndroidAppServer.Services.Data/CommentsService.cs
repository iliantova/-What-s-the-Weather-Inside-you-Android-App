namespace PsyhosAndroidAppServer.Services.Data
{
    using System;
    using System.Linq;
    using System.Linq.Expressions;
    using Contracts;
    using PsyhosAndroidAppServer.Data.Models;
    using PsyhosAndroidAppServer.Data.Repositories;

    public class CommentsService : IResultService
    {
        private readonly IRepository<Result> comments;

        public CommentsService(IRepository<Result> comments)
        {
            this.comments = comments;
        }

        public IQueryable<Result> GetAllByRealEstate(int realEstateId, int skip, int take)
        {
            return null;//this.SortAndPageComments(c => c.RealEstateId == realEstateId, skip, take);
        }

        public IQueryable<Result> GetById(int id)
        {
            return this.comments
                .All()
                .Where(r => r.Id == id);
        }

        public IQueryable<Result> GetAllByUser(string username, int skip, int take)
        {
            return null;// this.SortAndPageComments(c => c.User.UserName == username, skip, take);
        }

        public int AddNew(Result comment, string userId)
        {
            //comment.CreatedOn = DateTime.UtcNow;
            //comment.UserId = userId;

            this.comments.Add(comment);
            this.comments.SaveChanges();

            return comment.Id;
        }

        private IQueryable<Result> SortAndPageComments(
            Expression<Func<Result, bool>> filterExpression,
            int skip,
            int take)
        {
            return this.comments
                .All()
                .Where(filterExpression)
                //.OrderBy(c => c.CreatedOn)
                .Skip(skip)
                .Take(take);
        }
    }
}
