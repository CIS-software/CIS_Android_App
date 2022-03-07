package first.android.cis.ui.news.openedNewsFragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import first.android.cis.MainActivity

class DialogAskForDelete : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Вы действительно хотите удалить запись?")
            .setNegativeButton("Нет"){_,_ ->
                OpenedNewsFragment().deleteNews()
                Toast.makeText(activity, "Нажали нет",Toast.LENGTH_SHORT).show()

            }
            .setPositiveButton("Да"
            ) { _, _ ->
                Toast.makeText(activity, "Нажали да",Toast.LENGTH_SHORT).show()
            }
            .create()

    companion object {
        const val TAG = "Вопрос"
    }
}