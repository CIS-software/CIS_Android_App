package first.android.cis.presentation.news.mainNews

import android.annotation.SuppressLint
import android.content.Context
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
import first.android.cis.data.newsRepository.NewsRepository

class NewsFragment : Fragment() {
    private val myAdapter by lazy{ NewsAdapter() }
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var addNewsButton: Button
    private lateinit var viewModel: NewsViewModel
    private lateinit var viewModelFactory: NewsFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        val actionAddNews = NewsFragmentDirections.actionNavigationNewsToAddNewsFragment()
        _binding = FragmentNewsBinding.inflate(inflater,container,false)
        binding.let{
            addNewsButton = it.addNewsButton
        }
        addNewsButton.setOnClickListener{
            addNewsButton.findNavController().navigate(actionAddNews)
        }
        val navView: BottomNavigationView? = activity?.findViewById(R.id.navView)
        navView?.visibility = View.VISIBLE
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreference =  requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val accessToken = "Bearer " + sharedPreference.getString("access_token","empty_token")
        viewModelFactory = NewsFactory(repository = NewsRepository(), accessToken)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)
        viewModel.myNewsList.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setList(it) }
            } else {
                Toast.makeText(activity, "Ошибка подключения", Toast.LENGTH_LONG).show()
            }
        }
        setupRecyclerView(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView(view: View){
        if (myAdapter.itemCount==0){
            viewModel.getNewsVM()
        }
        val recyclerNews: RecyclerView by lazy{ view.findViewById(R.id.recyclerView)}
        recyclerNews.layoutManager = LinearLayoutManager(activity)
        recyclerNews.adapter = myAdapter
        myAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}