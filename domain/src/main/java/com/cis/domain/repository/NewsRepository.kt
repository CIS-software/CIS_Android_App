package com.cis.domain.repository

import com.cis.domain.models.news.NewsIdAndAccess
import com.cis.domain.models.news.NewsList
import com.cis.domain.models.news.NewsListForAdd
import com.cis.domain.models.news.NewsListItem
import retrofit2.Response

interface NewsRepository {
    suspend fun getNewsRepo(accessToken: String): Response<NewsList>

    suspend fun addNews(newsListForAdd: NewsListForAdd, accessToken: String): Response<NewsListItem>

    suspend fun deleteNews(newsIdAndAccess: NewsIdAndAccess)
}