package first.android.cis.presentation.news.mainNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import first.android.cis.R
import first.android.cis.databinding.FragmentNewsBinding
import com.example.data.newsRepository.NewsRepositoryImpl
import com.example.data.storage.sharedpref.SharedPrefTokensStorage
import com.example.data.tokensRepository.TokensRepositoryImpl
import com.cis.domain.usecases.signInUp.GetTokens

class NewsFragment : Fragment() {
    private val myAdapter by lazy{ NewsAdapter() }
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var addNewsButton: Button
    private lateinit var viewModel: NewsViewModel
    private lateinit var viewModelFactory: NewsFactory
    private val tokenStorage by lazy { SharedPrefTokensStorage(requireActivity().applicationContext) }
    private val tokensRepository by lazy {TokensRepositoryImpl(tokensStorage = tokenStorage)}
    private val getTokens by lazy{GetTokens(tokensRepository)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        val navView: BottomNavigationView? = activity?.findViewById(R.id.navView)
        val actionAddNews = NewsFragmentDirections.actionNavigationNewsToAddNewsFragment()
        navView?.visibility = View.VISIBLE
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        _binding = FragmentNewsBinding.inflate(inflater,container,false)
        binding.let{
            addNewsButton = it.addNewsButton
        }
        addNewsButton.setOnClickListener{
            addNewsButton.findNavController().navigate(actionAddNews)
        }

        val accessToken = getTokens.execute().accessToken
        viewModelFactory = NewsFactory(repository = NewsRepositoryImpl(), accessToken)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(NewsViewModel::class.java)
        viewModel.myNewsList.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setList(it) }
            } else {
                Toast.makeText(activity, "Ошибка подключения", Toast.LENGTH_LONG).show()
            }
        }
        setupRecyclerView(binding.root)
        return binding.root
    }

    private fun setupRecyclerView(view: View){
        if (viewModel.myNewsList.value == null){
            viewModel.getNewsVM()
        }
        val recyclerNews: RecyclerView by lazy{ view.findViewById(R.id.recyclerView)}
        recyclerNews.layoutManager = LinearLayoutManager(activity)
        recyclerNews.adapter = myAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}