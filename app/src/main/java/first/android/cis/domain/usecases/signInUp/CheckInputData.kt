package first.android.cis.domain.usecases.signInUp

import android.graphics.Color
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

class CheckInputData {

    fun checkEmail(email: String): Boolean {
        if ((email == "") or (" " in email)){
            return false
        } else {
            return true
        }
    }

    fun checkPassword(password: String): Boolean{
        if ((password == "") or (" " in password)){
            return false
        } else {
            return true
        }
    }

    fun checkUserInfo(
        userName: String, userSurname: String,
        userNameTextView: TextView, userSurnameTextView: TextView,
        activity: FragmentActivity?, dateOfBirth: String,
        selectedItem: String, townTextView: TextView,
        dateTextView: TextView
    ): Boolean {
        userNameTextView.setTextColor(Color.BLACK)
        userSurnameTextView.setTextColor(Color.BLACK)
        townTextView.setTextColor(Color.BLACK)
        dateTextView.setTextColor(Color.BLACK)
        var errorCounter = 0
        if (userName == ""){
            userNameTextView.setTextColor(Color.RED)
            errorCounter += 1
        }
        if (userSurname == ""){
            userSurnameTextView.setTextColor(Color.RED)
            errorCounter += 1
        }
        if (dateOfBirth == ""){
            errorCounter += 1
            dateTextView.setTextColor(Color.RED)
        }
        if (selectedItem == "Выбрать город"){
            errorCounter += 1
            townTextView.setTextColor(Color.RED)
        }
        if (errorCounter > 0){
            Toast.makeText(activity, "Ошибка! Данные введены некорректно!", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}