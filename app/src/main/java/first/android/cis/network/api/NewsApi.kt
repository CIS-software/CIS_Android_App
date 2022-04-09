package first.android.cis.network

import first.android.cis.domain.models.NewsList
import first.android.cis.domain.models.NewsListForAdd
import first.android.cis.domain.models.NewsListItem
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