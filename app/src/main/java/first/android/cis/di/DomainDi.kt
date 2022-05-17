package first.android.cis.di
import com.cis.domain.repository.UserRepository
import com.cis.domain.usecases.calendar.GetCalendar
import com.cis.domain.usecases.news.AddNews
import com.cis.domain.usecases.news.DeleteNews
import com.cis.domain.usecases.news.GetNews
import com.cis.domain.usecases.signInUp.DeleteTokens
import com.cis.domain.usecases.signInUp.GetTokens
import com.cis.domain.usecases.user.CreateUser
import com.cis.domain.usecases.user.GetUser
import com.cis.domain.usecases.user.SignInUser
import org.koin.dsl.module

val domainModule = module {
    //News
    factory<AddNews>{
        AddNews(newsRepository = get())
    }
    factory<DeleteNews> {
        DeleteNews(newsRepository = get())
    }
    factory<GetNews> {
        GetNews(newsRepository = get())
    }

    //SignInUp
    factory<DeleteTokens> {
        DeleteTokens(tokensRepository = get())
    }
    factory<GetTokens> {
        GetTokens(tokensRepository = get())
    }

    //User
    factory<GetUser>{
        GetUser(userRepository = get())
    }
    factory<CreateUser>{
        CreateUser(userRepository = get())
    }
    factory<SignInUser>{
        SignInUser(userRepository = get())
    }

    //Calendar
    factory<GetCalendar> {
        GetCalendar(calendarRepository = get())
    }
}