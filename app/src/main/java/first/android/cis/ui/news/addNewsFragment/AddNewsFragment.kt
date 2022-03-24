package first.android.cis.ui.news.addNewsFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import first.android.cis.R
import first.android.cis.models.NewsListForAdd
import first.android.cis.network.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.add_news_fragment, container, false)
        val postNewsButton: Button = root.findViewById(R.id.post_news_butt)
        val editHeading: EditText = root.findViewById(R.id.inputHeadingEditT)
        val editDiscript: EditText = root.findViewById(R.id.inputDiscriptEditT)
        postNewsButton.setOnClickListener{
            val heading = editHeading.text.toString()
            val discript = editDiscript.text.toString()
            addNews(heading,discript, editHeading, editDiscript)
        }
        val navView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navView.visibility = View.GONE
        return root
    }
    //TODO: Надо подумать стоит ли реализовывать кнопку "добавить" через init
    private fun addNews(heading: String,
                        discript: String,
                        editHeading: EditText,
                        editDiscript: EditText){
        //TODO: Окно "Успех, запись добавлена" вылезит, даже если произойдет ошибка при запросе
        val newsList = NewsListForAdd(newsTitle = heading,
            newsDescription = discript, newsPhoto = "" , newsTimeDate = null)
        //TODO: Надо сделать обработку ошибок от сервера
        val sharedPreference =  requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val accessToken = "Bearer " + sharedPreference.getString("access_token","empty_token")
        CoroutineScope(Dispatchers.Main).launch {
            Retrofit.newsApi.addNews(newsList, accessToken)
        }
        DialogConfirmed().show(childFragmentManager, DialogConfirmed.TAG)
        editHeading.text = null
        editDiscript.text = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}