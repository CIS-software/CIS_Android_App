package first.android.cis.presentation.news.addNews

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import first.android.cis.data.newsRepository.NewsRepositoryImpl
import first.android.cis.data.tokens.TokensRepositoryImpl
import first.android.cis.databinding.AddNewsFragmentBinding
import first.android.cis.domain.models.news.NewsListForAdd
import first.android.cis.domain.usecases.news.AddNews
import first.android.cis.domain.usecases.signInUp.GetTokens
import first.android.cis.network.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewsFragment : Fragment() {
    private var _binding: AddNewsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var postNewsButton: Button
    private lateinit var inputHeadingEditT: EditText
    private lateinit var inputDiscriptEditT: EditText
    private val tokensRepository by lazy{TokensRepositoryImpl(context = requireActivity())}
    private val newsRepository by lazy{NewsRepositoryImpl()}
    private val getTokens by lazy{GetTokens(tokensRepository)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = AddNewsFragmentBinding.inflate(inflater, container, false)
        binding.let{
            postNewsButton = it.postNewsButton
            inputHeadingEditT = it.inputHeadingEditT
            inputDiscriptEditT = it.inputDiscriptEditT
        }
        postNewsButton.setOnClickListener{
            val heading = inputHeadingEditT.text.toString()
            val discript = inputDiscriptEditT.text.toString()
            addNews(heading,discript, inputHeadingEditT, inputDiscriptEditT)
        }
        return binding.root
    }

    private fun addNews(heading: String,
                        discript: String,
                        inputHeadingEditT: EditText,
                        inputDiscriptEditT: EditText){
        val newsList = NewsListForAdd(newsTitle = heading,
            newsDescription = discript, newsPhoto = "" , newsTimeDate = null)
        val accessToken = getTokens.execute().accessToken
        val addNews = AddNews(newsRepository = newsRepository)
        CoroutineScope(Dispatchers.Main).launch {
            addNews.execute(newsListForAdd = newsList, accessToken)
        }
        DialogConfirmed().show(childFragmentManager, DialogConfirmed.TAG)
        inputHeadingEditT.text = null
        inputDiscriptEditT.text = null
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}