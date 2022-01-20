package first.android.cis.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import first.android.cis.R
import first.android.cis.network.QuestApp
import first.android.cis.network.newsAPI.NewsListResponse

class NewsFragment : Fragment() {




    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?
    {
        //newsViewModel =
          //      ViewModelProvider(this).get(NewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        val recyclerNews: RecyclerView =  root.findViewById(R.id.recyclerView)
        recyclerNews.layoutManager = LinearLayoutManager(this.context)
        val names = arrayListOf("Заголовок 1", "Заголовок2", "Заголовок3", "Заголовок4")
        recyclerNews.adapter = NewsAdapter(names)
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.fetchNewsList((activity?.application as QuestApp).newsApi)

    }


}

