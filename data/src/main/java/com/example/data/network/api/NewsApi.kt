package com.example.data.network.api

import com.cis.domain.models.news.NewsList
import com.cis.domain.models.news.NewsListForAdd
import com.cis.domain.models.news.NewsListItem
import retrofit2.Response
import retrofit2.http.*

interface NewsApi {
    @GET("/news")
    suspend fun getNews(@Header("Authorization") accessToken: String): Response<NewsList>

    @POST("/news")
    suspend fun addNews(@Body newsList: NewsListForAdd, @Header("Authorization") accessToken: String): Response<NewsListItem>

    @DELETE("/news/{id}")
    suspend fun deleteNews(@Path("id")id: Int, @Header("Authorization") accessToken: String): Response<Throwable>
}