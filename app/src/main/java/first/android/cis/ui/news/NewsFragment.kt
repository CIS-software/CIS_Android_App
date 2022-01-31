package first.android.cis.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import first.android.cis.R

class NewsFragment : Fragment() {

    lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        val viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        val recyclerNews: RecyclerView =  root.findViewById(R.id.recyclerView)
        recyclerNews.layoutManager = LinearLayoutManager(this.context)
        adapter = NewsAdapter()
        recyclerNews.adapter = adapter
        viewModel.getNewsVM()
        viewModel.myNewsList.observe(viewLifecycleOwner,{list ->
            if(list.isSuccessful){
                list.body()?.let { adapter.setList(it) }
            }else{
                Toast.makeText(activity, "Ошибка подключения", Toast.LENGTH_LONG).show()
            }

        })
        return root
    }
}

