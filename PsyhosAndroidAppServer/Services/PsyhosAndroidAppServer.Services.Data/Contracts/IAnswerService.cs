using PsyhosAndroidAppServer.Data.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PsyhosAndroidAppServer.Services.Data.Contracts
{
    public interface IAnswerService
    {
        IQueryable<Answer> GetAllByQuestionId(int questionId, int take); // int questionId, int skip, int take

        IQueryable<Answer> GetById(int id);

        IQueryable<Answer> GetFourSelectedAnswers();

        int AddNew(Answer answer, string userId);
    }
}
