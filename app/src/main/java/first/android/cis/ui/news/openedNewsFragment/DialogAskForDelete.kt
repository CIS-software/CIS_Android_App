package first.android.cis.ui.news.openedNewsFragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import first.android.cis.network.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DialogAskForDelete(private val newsId: Int) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Вы действительно хотите удалить запись?")
            .setNegativeButton("Нет"){_,_ ->}
            .setPositiveButton("Да"
            ) { _, _ ->
                deleteNews()
                Toast.makeText(activity, "Запись удалена",Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()
            }
            .create()

    private fun deleteNews(){
        CoroutineScope(Dispatchers.Main).launch {
            Retrofit.newsApi.deleteNews(newsId)
        }
    }

    companion object {
        const val TAG = "Вопрос"
    }
}