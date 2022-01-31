package first.android.cis.network.newsAPI

import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("./events")
    suspend fun getNews(): Response<NewsList>
}