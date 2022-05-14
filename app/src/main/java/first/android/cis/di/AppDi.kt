package first.android.cis.di

import first.android.cis.presentation.news.addNews.AddNewsViewModel
import first.android.cis.presentation.news.mainNews.NewsViewModel
import first.android.cis.presentation.news.openedNews.OpenedNewsViewModel
import first.android.cis.presentation.profile.ProfileViewModel
import first.android.cis.presentation.signUpIn.signUp.signUpStep2.Step2ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<NewsViewModel>{
        NewsViewModel(getTokens = get(), getNews = get())
    }

    viewModel<ProfileViewModel>{
        ProfileViewModel(userRepository = get(),
            getTokens = get(),
            tokensRepository = get())
    }

    viewModel<AddNewsViewModel>{
        AddNewsViewModel(addNews = get(), getTokens = get())
    }

    viewModel<OpenedNewsViewModel>{
        OpenedNewsViewModel(getTokens = get(), deleteNews = get())
    }

    viewModel<Step2ViewModel>{
        Step2ViewModel(createUser = get())
    }
}