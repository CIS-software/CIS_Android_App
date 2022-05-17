package first.android.cis.presentation.calendar

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cis.domain.models.calendar.CalendarList
import com.cis.domain.usecases.calendar.GetCalendar
import com.cis.domain.usecases.signInUp.GetTokens
import kotlinx.coroutines.launch
import retrofit2.Response

class CalendarViewModel(private val getTokens: GetTokens, private val getCalendar: GetCalendar) : ViewModel() {

    val myCalendarList: MutableLiveData<Response<CalendarList>> = MutableLiveData()

    fun getCalendar(){
        viewModelScope.launch {
            try{
                myCalendarList.value = getCalendar.execute(accessToken = getTokens.execute().accessToken)
            } catch(exception: Exception){
                Log.d("NETWORK ERROR", exception.toString() )
            }
        }
    }
}