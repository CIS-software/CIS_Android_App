package first.android.cis.ui.news.mainNewsFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import first.android.cis.R
import first.android.cis.models.NewsListItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var listNews = emptyList<NewsListItem>()

    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val headingTextView: TextView = itemView.findViewById(R.id.textViewHeading)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
        val dateTime: TextView = itemView.findViewById(R.id.dateTimeView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.maket_news_outside, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.headingTextView.text = listNews[position].newsTitle
        holder.descriptionTextView.text = listNews[position].newsDescription
        holder.dateTime.text = formatingDate(listNews[position].newsTimeDate)
        val newsHeading: String = listNews[position].newsTitle
        val newsDiscript: String = listNews[position].newsDescription
        val newsId: Int = listNews[position].newsId
        val action = NewsFragmentDirections.actionNavigationNewsToOpenedNews2(
            newsId,
            newsHeading,
            newsDiscript,
        )
        holder.itemView.setOnClickListener{
            holder.itemView.findNavController().navigate(action)
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

    fun formatingDate(dateTimeStrArg: String?): String {
        var dateTimeStr: String? = dateTimeStrArg
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val formattedDateTime = LocalDateTime.parse(dateTimeStr, formatter)
        dateTimeStr = formattedDateTime.hour.toString() +":"+ formattedDateTime.minute + " " +
                formattedDateTime.dayOfMonth + " " + formattedDateTime.month
        return dateTimeStr
    }
}