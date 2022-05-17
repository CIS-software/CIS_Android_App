package first.android.cis.presentation.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import first.android.cis.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalendarFragment : Fragment() {

    private val calendarViewModel by viewModel<CalendarViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)

        return root
    }
}