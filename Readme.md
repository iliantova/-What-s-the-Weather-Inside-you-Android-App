PsychoApp Quiz Android
======================

> PsychoAp Quiz is jAndroid application made with Java, C#, MSSQL and
> XML with Android Studio used as a IDE.

The main purpose of this app is to create a mobile platform quick quiz that is returning the current psychological condition of the user that takes it.
The app contains user authentication (own and Google) and a control panel.
From the control panel you can start a new quiz with random qestions and after selecting the answers the user is redirected to his result and recommendations.
The control panel also has statistic page and calendar page (in which a previously taken results can be seen)

The code used in this app is written in multiple platforms as follows :

 1. Java part (User interface, UX and main application logic)
 2. C# for backend services (REST Web API)
 3. MSSQL database with Code First approach
 4. XML for the presentation layouts

Application workflow
--------------------

> The app requares Android mobile device (compatible with all versioan
> from IceCream Sandwhich 4.0.3 (minimal SDK) The google login requares
> Google Services installed as well. The app starts with basic
> authenctication pages (login / register) with the additional option to
> log with Google account. If the user prefers there is an option to
> take the quiz anonymiously but however he will not have access to the
> control page. After succesfull login the app redirects to the control
> page from where there are four options

 - calendar
 - statistics
 - quiz
 - last result

> The quiz page connects to the WebAPI RESTfull server and loads ten
> random questions. After the quiz is taken the app redirects to the
> detailed view of the current result (LastResult).

*Full source code can be found at
**[github source code here](https://github.com/iliantova/-What-s-the-Weather-Inside-you-Android-App)**

*some code samples that shows the app workflow :*

FragmentStatePagerAdapter usage to change the quiz questions.

    public static class QuizQuestionsPagerAdapter extends FragmentStatePagerAdapter {

        public QuizQuestionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        
        @Override
         public Fragment getItem(int position) {
            Fragment fragment = new QuizActivityFragment();
            Bundle args = new Bundle();

            args.putInt(QuizActivityFragment.ARG_OBJECT, position + 1);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Question #" + (position + 1);
        }
    }

*C# Example*

    namespace PsyhosAndroidAppServer.Data
{
    using Models;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;

    public interface IPsyhosAndroidAppServerDbContext
    {
        IDbSet<User> Users { get; set; }

        DbSet<TEntity> Set<TEntity>() where TEntity : class;

        DbEntityEntry<TEntity> Entry<TEntity>(TEntity entity) where TEntity : class;

        int SaveChanges();

        void Dispose();
    }
}


*XML Exmaple*

    <layer-list>
    <item >
        <shape
            android:shape="rectangle">
            <solid android:color="@color/themeGrayGreen" />
            <corners android:radius="10dp"/>
        </shape>
    </item>
    <item android:right="2dp" android:left="1dp" android:bottom="2dp">
        <shape
            android:shape="rectangle">
            <solid android:color="@color/themeGreenDark"/>
            <corners android:radius="10dp"/>
        </shape>
    </item>
</layer-list>


The contributors of this app are Nick Iliev and Ilianna Antova - for further dfetails contact us at

[darkyto](https://github.com/darkyto)
[iliantova](https://github.com/iliantova)