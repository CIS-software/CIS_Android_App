package first.android.cis.presentation.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cis.domain.models.calendar.CalendarItem
import first.android.cis.R

class CalendarAdapter: RecyclerView.Adapter<CalendarAdapter.CalendarHolder>() {

    private var listCalender = emptyList<CalendarItem>()

    class CalendarHolder(item: View): RecyclerView.ViewHolder(item) {
        val dayOfWeekView: TextView = item.findViewById(R.id.textViewDay)
        val eventView: TextView = item.findViewById(R.id.textViewEvent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.maket_calendar, parent, false)
        return CalendarHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarHolder, position: Int) {
        holder.dayOfWeekView.text = listCalender[position].dayOfWeek
        holder.eventView.text = listCalender[position].event
    }

    override fun getItemCount(): Int {
        return listCalender.size
    }

    fun setList(list: List<CalendarItem>){
        listCalender = list
    }
}