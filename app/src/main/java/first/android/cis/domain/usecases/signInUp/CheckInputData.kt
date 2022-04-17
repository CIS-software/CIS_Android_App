package first.android.cis.domain.usecases.signInUp

class CheckInputData {

    fun checkEmail(email: String): Boolean {
        return true
        //return !((email == "") or (" " in email))
    }

    fun checkPassword(password: String): Boolean{
        return !((password == "") or (" " in password))
    }
    fun checkUserName(userName: String): Boolean{
        return userName != ""
    }

    fun checkDateofBirth(dateBirth: String): Boolean{
        return dateBirth != ""
    }

    fun checkUserTown(town: String): Boolean{
        return town != "Выбрать город"
    }




}