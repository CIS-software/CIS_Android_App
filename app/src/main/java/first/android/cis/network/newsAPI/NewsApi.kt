package first.android.cis.network.newsAPI

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers


interface NewsApi {

    @GET("./events")
    //@Headers("Content-Type: application/json")
    fun getNews(): Single<NewsListResponse>
}