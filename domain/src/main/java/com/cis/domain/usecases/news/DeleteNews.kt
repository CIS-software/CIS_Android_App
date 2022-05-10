package com.cis.domain.usecases.news

import com.cis.domain.models.news.NewsIdAndAccess
import com.cis.domain.repository.NewsRepository

class DeleteNews(private val newsRepository: NewsRepository) {
    suspend fun execute(newsIdAndAccess: NewsIdAndAccess){
        return newsRepository.deleteNews(newsIdAndAccess = newsIdAndAccess)
    }
}