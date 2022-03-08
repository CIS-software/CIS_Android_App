package first.android.cis.network

import first.android.cis.models.NewsList
import first.android.cis.models.NewsListForAdd
import first.android.cis.models.NewsListItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface NewsApi {
    @GET("/news")
    suspend fun getNews(): Response<NewsList>

    @POST("/news")
    suspend fun addNews(@Body newsList: NewsListForAdd): NewsListItem

    @DELETE("/news/{id}")
    suspend fun deleteNews(@Path("id")id: Int)

}