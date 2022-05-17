package first.android.cis.presentation.calendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cis.domain.models.calendar.CalendarList
import retrofit2.Response

class CalendarViewModel : ViewModel() {

    val myCalendarList: MutableLiveData<Response<CalendarList>> = MutableLiveData()

}