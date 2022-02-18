package first.android.cis.network.getNews

import first.android.cis.models.NewsList
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("./news")
    suspend fun getNews(): Response<NewsList>
}