package first.android.cis.ui.news.addNewsFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import first.android.cis.R
import first.android.cis.network.Retrofit
import first.android.cis.models.NewsListItem
import first.android.cis.network.postNews.PostApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewsFragment : Fragment() {

    private lateinit var viewModel: AddNewsViewModel

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

    private fun addNews(heading: String, discript: String,
                        editHeading: EditText, editDiscript: EditText){
        val retroft = Retrofit.buildService(PostApi::class.java)
        val newsList = NewsListItem(newsId = null, newsTitle = heading,
            newsDescription = discript, newsPhoto = null , newsTimeDate = null)
        CoroutineScope(Dispatchers.Main).launch {
            retroft.addNews(newsList)
        }
        DialogConfirmed().show(childFragmentManager, DialogConfirmed.TAG)
        editHeading.text = null
        editDiscript.text = null
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddNewsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = AddNewsFragment()
    }

}