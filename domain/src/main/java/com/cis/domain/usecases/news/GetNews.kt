package com.cis.domain.usecases.news

import com.cis.domain.models.news.NewsList
import com.cis.domain.repository.NewsRepository
import retrofit2.Response

class GetNews(private val newsRepository: NewsRepository) {
    suspend fun execute(accessToken: String): Response<NewsList>{
        return newsRepository.getNewsRepo(accessToken = accessToken)
    }
}