package first.android.cis.network.getNews

import first.android.cis.models.NewsList
import first.android.cis.models.NewsListForAdd
import first.android.cis.models.NewsListItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface NewsApi {
    @GET("./news")
    suspend fun getNews(): Response<NewsList>

    @DELETE("./news")
    suspend fun deleteNews(@Field("id")id: Int): Call<Unit>

    @POST("./news")
    suspend fun addNews(@Body newsList: NewsListForAdd): NewsListItem
}