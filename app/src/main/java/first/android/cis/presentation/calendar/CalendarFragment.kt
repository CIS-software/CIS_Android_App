package first.android.cis.presentation.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cis.domain.models.calendar.CalendarItem
import com.cis.domain.models.calendar.CalendarList
import first.android.cis.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalendarFragment : Fragment() {

    private val calendarViewModel by viewModel<CalendarViewModel>()
    private val calendarAdapter by lazy{CalendarAdapter()}

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)
        val myList: List<CalendarItem> = listOf(
            CalendarItem(0,"Пн", "Нет тренировки"),
            CalendarItem(1,"Вт", "Тренировка в 19:00"),
            CalendarItem(2,"Ср", "Нет тренировки"),
            CalendarItem(3,"Чт", "Тренировка в 19:00"),
            CalendarItem(4,"Пт", "Нет тренировки"),
            CalendarItem(5,"Сб", "Нет тренировки"),
            CalendarItem(6,"Вс", "Нет тренировки")
        )
        val recyclerCalendar: RecyclerView by lazy {root.findViewById(R.id.calendarRecyclerView)}
        recyclerCalendar.layoutManager = LinearLayoutManager(activity)
        recyclerCalendar.adapter = calendarAdapter
        calendarAdapter.setList(myList)
        return root
    }
}