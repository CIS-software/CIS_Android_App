package first.android.cis.presentation.news.openedNews

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ASK_FOR_DELETE = "Вы действительно хотите удалить запись?"
private const val YES = "Да"
private const val NO = "Нет"
private const val QUESTION = "Вопрос"

class DialogAskForDelete(private val newsId: Int) : DialogFragment() {
    private val openedNewsViewModel: OpenedNewsViewModel by viewModel<OpenedNewsViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(ASK_FOR_DELETE)
            .setNegativeButton(NO){ _, _ ->}
            .setPositiveButton(YES) { _, _ ->
                openedNewsViewModel.deleteNews(newsId = newsId)
                activity?.onBackPressed()
            }
            .create()

    companion object {
        const val TAG = QUESTION
    }
}