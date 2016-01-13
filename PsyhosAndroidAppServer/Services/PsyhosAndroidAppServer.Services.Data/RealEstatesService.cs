namespace PsyhosAndroidAppServer.Services.Data
{
    using System;
    using System.Linq;
    using Contracts;
    using PsyhosAndroidAppServer.Data.Models;
    using PsyhosAndroidAppServer.Data.Repositories;

    public class QuestionEstatesService : IQuestionService
    {
        private readonly IRepository<Question> realEstates;

        public QuestionEstatesService(IRepository<Question> realEstates)
        {
            this.realEstates = realEstates;
        }
        
        public IQueryable<Question> GetAll(int skip, int take)
        {
            return this.realEstates
                .All()
                .OrderByDescending(r => r.Id)
                .Skip(skip)
                .Take(take);
        }

        public IQueryable<Question> GetById(int id)
        {
            return this.realEstates
                .All()
                .Where(r => r.Id == id);
        }

        public int AddNew(Question newRealEstate, string userId)
        {
            //newRealEstate.t = DateTime.UtcNow;
            //newRealEstate.UserId = userId;

            this.realEstates.Add(newRealEstate);
            this.realEstates.SaveChanges();

            return newRealEstate.Id;
        }
    }
}
