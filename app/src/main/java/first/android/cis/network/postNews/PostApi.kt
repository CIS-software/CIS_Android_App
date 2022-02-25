package first.android.cis.network.postNews

import first.android.cis.models.NewsListForAdd
import first.android.cis.models.NewsListItem
import retrofit2.http.Body
import retrofit2.http.POST

interface PostApi {
    @POST("./news")
    suspend fun addNews(@Body newsList: NewsListForAdd): NewsListItem
}