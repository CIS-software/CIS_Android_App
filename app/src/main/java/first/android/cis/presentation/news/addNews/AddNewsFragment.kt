package first.android.cis.presentation.news.addNews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import first.android.cis.databinding.AddNewsFragmentBinding
import com.cis.domain.models.news.NewsListForAdd
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNewsFragment : Fragment() {
    private val addNewsViewModel by viewModel<AddNewsViewModel>()
    private var _binding: AddNewsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var postNewsButton: Button
    private lateinit var inputHeadingEditT: EditText
    private lateinit var inputDiscriptEditT: EditText

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

        addNewsViewModel.itemForAddNews.observe(viewLifecycleOwner){ response ->
            if (response.isSuccessful){
                DialogConfirmed().show(childFragmentManager, DialogConfirmed.TAG)
                inputHeadingEditT.text = null
                inputDiscriptEditT.text = null
            } else {
                Toast.makeText(activity, "Ошибка подключение", Toast.LENGTH_LONG).show()
            }
        }
        postNewsButton.setOnClickListener{
            val heading = inputHeadingEditT.text.toString()
            val discript = inputDiscriptEditT.text.toString()
            createNews(heading,discript)
        }
        return binding.root
    }

    private fun createNews(heading: String, discript: String){
        val newsList = NewsListForAdd(newsTitle = heading,
            newsDescription = discript, newsPhoto = "" , newsTimeDate = null)
        addNewsViewModel.addNews(newsListForAdd = newsList)
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