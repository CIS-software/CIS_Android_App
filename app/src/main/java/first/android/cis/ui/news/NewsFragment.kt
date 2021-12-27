package first.android.cis.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import first.android.cis.R
import first.android.cis.ui.events.EventsViewModel

class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
                ViewModelProvider(this).get(NewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        val recyclerNews: RecyclerView =  root.findViewById(R.id.recyclerView)
        recyclerNews.layoutManager = LinearLayoutManager(this.context)
        val names = arrayListOf("Заголовок 1", "Заголовок2", "Заголовок3", "Заголовок4")
        recyclerNews.adapter = NewsAdapter(names)
        return root
    }
}

