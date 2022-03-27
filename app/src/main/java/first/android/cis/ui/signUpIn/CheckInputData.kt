package first.android.cis.ui.signUpIn

import android.graphics.Color
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

class CheckInputData {
    fun checkAuthData(
        email: String,
        password: String,
        emailTextView: TextView,
        passwordTextView: TextView,
        activity: FragmentActivity?
    ): Boolean {
        emailTextView.setTextColor(Color.BLACK)
        passwordTextView.setTextColor(Color.BLACK)
        var errorCounter = 0
        if ((email == "") or (" " in email)){
            emailTextView.setTextColor(Color.RED)
            errorCounter += 1
        }
        if ((password == "") or (" " in password)){
            passwordTextView.setTextColor(Color.RED)
            errorCounter += 1
        }
        if (errorCounter > 0){
            Toast.makeText(activity, "Ошибка! Данные введены некорректно!", Toast.LENGTH_LONG).show()
            return false
        }
        return true
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