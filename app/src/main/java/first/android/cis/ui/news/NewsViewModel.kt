package first.android.cis.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import first.android.cis.network.newsAPI.NewsApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel : ViewModel() {

    private val _headingList = MutableLiveData<List<String>>().apply {
        value = arrayListOf("Заголовок 1", "Заголовок2", "Заголовок3", "Заголовок4")
    }
    val text: LiveData<List<String>> = _headingList

    private val compositeDisposable = CompositeDisposable() //28:00 тайм код

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun fetchNewsList(newsApi: NewsApi?){
        newsApi?.let{
            compositeDisposable.add(newsApi.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                },{

                }))
        }
    }
}