﻿namespace PsyhosAndroidAppServer.Services.Data.Contracts
{
    using System.Linq;
    using PsyhosAndroidAppServer.Data.Models;

    public interface IRealEstatesService
    {
        IQueryable<RealEstate> GetAll(int skip, int take);

        IQueryable<RealEstate> GetById(int id);
        
        int AddNew(RealEstate newRealEstate, string userId);
    }
}
