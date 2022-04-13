package first.android.cis.data.network.api

import first.android.cis.domain.models.news.NewsList
import first.android.cis.domain.models.news.NewsListForAdd
import first.android.cis.domain.models.news.NewsListItem
import retrofit2.Response
import retrofit2.http.*

interface NewsApi {
    @GET("/news")
    suspend fun getNews(@Header("Authorization") accessToken: String): Response<NewsList>

    @POST("/news")
    suspend fun addNews(@Body newsList: NewsListForAdd, @Header("Authorization") accessToken: String): NewsListItem

    @DELETE("/news/{id}")
    suspend fun deleteNews(@Path("id")id: Int, @Header("Authorization") accessToken: String)
}