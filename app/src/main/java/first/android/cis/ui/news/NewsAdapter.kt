package first.android.cis.ui.news

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import first.android.cis.R
import first.android.cis.network.newsAPI.NewsListItem

class NewsAdapter(): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var listNews = emptyList<NewsListItem>()

    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var headingTextView: TextView = itemView.findViewById(R.id.textViewHeading)
        var descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.maket_news_outside, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.headingTextView.text = listNews[position].newsTitle
        holder.descriptionTextView.text = listNews[position].newsDescription
        holder.itemView.setOnClickListener{
            holder.itemView.findNavController().navigate(R.id.action_navigation_news_to_openedNews2)
        }

    }
    override fun getItemCount(): Int {
        return listNews.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NewsListItem>){
        listNews = list
        notifyDataSetChanged()
    }
}