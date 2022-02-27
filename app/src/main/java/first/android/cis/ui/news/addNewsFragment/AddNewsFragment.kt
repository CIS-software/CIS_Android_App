package first.android.cis.ui.news.addNewsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
        val root = inflater.inflate(R.layout.add_news_fragment, container, false)
        val postNewsButton: Button = root.findViewById(R.id.post_news_butt)
        val editHeading: EditText = root.findViewById(R.id.inputHeadingEditT)
        val editDiscript: EditText = root.findViewById(R.id.inputDiscriptEditT)
        postNewsButton.setOnClickListener{
            val heading = editHeading.text.toString()
            val discript = editDiscript.text.toString()
            addNews(heading,discript, editHeading, editDiscript)
        }
        return root
    }

    private fun addNews(heading: String,
                        discript: String,
                        editHeading: EditText,
                        editDiscript: EditText){
        val newsList = NewsListForAdd(newsTitle = heading,
            newsDescription = discript, newsPhoto = null , newsTimeDate = null)
        CoroutineScope(Dispatchers.Main).launch {
            Retrofit.newsApi.addNews(newsList)
        }
        DialogConfirmed().show(childFragmentManager, DialogConfirmed.TAG)
        editHeading.text = null
        editDiscript.text = null
    }

    companion object {
        fun newInstance() = AddNewsFragment()
    }

}