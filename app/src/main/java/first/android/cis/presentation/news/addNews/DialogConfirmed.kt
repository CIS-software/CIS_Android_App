package first.android.cis.ui.news.addNewsFragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogConfirmed : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Запись успешно добавлена!")
            .setPositiveButton("Окей") { _,_ -> }
            .create()

    companion object {
        const val TAG = "Успех"
    }
}