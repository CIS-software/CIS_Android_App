package first.android.cis.di

import com.cis.domain.repository.CalendarRepository
import com.cis.domain.repository.NewsRepository
import com.cis.domain.repository.TokensRepository
import com.cis.domain.repository.UserRepository
import com.example.data.calendar.CalendarRepositoryImpl
import com.example.data.newsRepository.NewsRepositoryImpl
import com.example.data.storage.TokensStorage
import com.example.data.storage.sharedpref.SharedPrefTokensStorage
import com.example.data.tokensRepository.TokensRepositoryImpl
import com.example.data.userRepository.UserRepositoryImpl
import org.koin.dsl.module


val dataModule = module {
    single<TokensStorage> {
        SharedPrefTokensStorage(context = get())
    }
    single<TokensRepository>{
        TokensRepositoryImpl(tokensStorage = get())
    }
    single<UserRepository>{
        UserRepositoryImpl(context = get())
    }
    single<NewsRepository>{
        NewsRepositoryImpl()
    }
    single<CalendarRepository>{
        CalendarRepositoryImpl()
    }
}