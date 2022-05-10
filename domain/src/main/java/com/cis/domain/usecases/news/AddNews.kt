package com.cis.domain.usecases.news

import com.cis.domain.models.news.NewsListForAdd
import com.cis.domain.models.news.NewsListItem
import com.cis.domain.repository.NewsRepository

class AddNews(private val newsRepository: NewsRepository) {
    suspend fun execute(newsListForAdd: NewsListForAdd, accessToken: String): NewsListItem {
        return newsRepository.addNews(newsListForAdd = newsListForAdd, accessToken = accessToken)
    }
}