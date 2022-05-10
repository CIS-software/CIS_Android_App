package com.example.data.newsRepository

import first.android.cis.domain.models.news.NewsIdAndAccess
import first.android.cis.domain.models.news.NewsList
import first.android.cis.domain.models.news.NewsListForAdd
import first.android.cis.domain.models.news.NewsListItem
import first.android.cis.domain.repository.NewsRepository
import com.example.data.network.Retrofit
import retrofit2.Response

class NewsRepositoryImpl: NewsRepository{
  override suspend fun getNewsRepo(accessToken: String): Response<NewsList> {
       return Retrofit.newsApi.getNews(accessToken)
   }

    override suspend fun addNews(newsListForAdd: NewsListForAdd, accessToken: String): NewsListItem {
        return Retrofit.newsApi.addNews(newsList = newsListForAdd, accessToken)
    }

    override suspend fun deleteNews(newsIdAndAccess: NewsIdAndAccess) {
        return Retrofit.newsApi.deleteNews(id = newsIdAndAccess.id, accessToken = newsIdAndAccess.accessToken)
    }
}