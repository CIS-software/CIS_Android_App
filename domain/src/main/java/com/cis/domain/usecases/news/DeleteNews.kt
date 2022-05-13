package com.cis.domain.usecases.news

import com.cis.domain.models.news.NewsIdAndAccess
import com.cis.domain.repository.NewsRepository
import retrofit2.Response

class DeleteNews(private val newsRepository: NewsRepository) {
    suspend fun execute(newsIdAndAccess: NewsIdAndAccess): Response<Throwable>{
        return newsRepository.deleteNews(newsIdAndAccess = newsIdAndAccess)
    }
}