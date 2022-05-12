package com.example.data.newsRepository

import com.cis.domain.repository.NewsRepository
import com.cis.domain.models.news.NewsIdAndAccess
import com.cis.domain.models.news.NewsList
import com.cis.domain.models.news.NewsListForAdd
import com.cis.domain.models.news.NewsListItem
import com.example.data.network.Retrofit
import retrofit2.Response

class NewsRepositoryImpl: NewsRepository {
  override suspend fun getNewsRepo(accessToken: String): Response<NewsList> {
       return Retrofit.newsApi.getNews(accessToken)
   }

    override suspend fun addNews(newsListForAdd: NewsListForAdd, accessToken: String): Response<NewsListItem> {
        return Retrofit.newsApi.addNews(newsList = newsListForAdd, accessToken)
    }

    override suspend fun deleteNews(newsIdAndAccess: NewsIdAndAccess) {
        return Retrofit.newsApi.deleteNews(id = newsIdAndAccess.id, accessToken = newsIdAndAccess.accessToken)
    }
}