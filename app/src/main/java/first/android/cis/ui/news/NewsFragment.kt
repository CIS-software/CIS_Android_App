package first.android.cis.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import first.android.cis.R
import first.android.cis.databinding.FragmentNewsBinding
import first.android.cis.ui.news.openedNews.OpenedNewsViewModel

class NewsFragment : Fragment() {
    private val openHeading: OpenedNewsViewModel by activityViewModels()

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
            list.body()?.let { adapter.setList(it) }
        })
        return root
    }
}

