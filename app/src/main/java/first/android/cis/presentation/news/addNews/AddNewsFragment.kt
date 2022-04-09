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
import first.android.cis.databinding.AddNewsFragmentBinding
import first.android.cis.models.NewsListForAdd
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
        //TODO: Окно "Успех, запись добавлена" вылезит, даже если произойдет ошибка при запросе
        val newsList = NewsListForAdd(newsTitle = heading,
            newsDescription = discript, newsPhoto = "" , newsTimeDate = null)
        val sharedPreference =  requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val accessToken = "Bearer " + sharedPreference.getString("access_token","empty_token")
        CoroutineScope(Dispatchers.Main).launch {
            Retrofit.newsApi.addNews(newsList, accessToken)
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