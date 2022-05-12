package first.android.cis.di

import first.android.cis.presentation.news.mainNews.NewsViewModel
import first.android.cis.presentation.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<NewsViewModel>{
        NewsViewModel(repository = get(), getTokens = get())
    }

    viewModel<ProfileViewModel>{
        ProfileViewModel(userRepository = get(),
            getTokens = get(),
            tokensRepository = get())
    }
}