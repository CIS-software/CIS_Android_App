package first.android.cis.presentation.news.openedNews

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import first.android.cis.data.newsRepository.NewsRepositoryImpl
import first.android.cis.data.storage.sharedpref.SharedPrefTokensStorage
import first.android.cis.data.tokensRepository.TokensRepositoryImpl
import first.android.cis.domain.models.news.NewsIdAndAccess
import first.android.cis.domain.usecases.news.DeleteNews
import first.android.cis.domain.usecases.signInUp.GetTokens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ASK_FOR_DELETE = "Вы действительно хотите удалить запись?"
private const val YES = "Да"
private const val NO = "Нет"
private const val DELETE_SUCCESS = "Запись удалена"
private const val QUESTION = "Вопрос"

class DialogAskForDelete(private val newsId: Int) : DialogFragment() {
    private val tokenStorage by lazy { SharedPrefTokensStorage(requireActivity().applicationContext) }
    private val tokensRepository by lazy{TokensRepositoryImpl(tokenStorage)}
    private val getTokens by lazy{ GetTokens(tokensRepository) }
    private val newsRepository by lazy{NewsRepositoryImpl()}
    private val deleteNews by lazy{DeleteNews(newsRepository)}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(ASK_FOR_DELETE)
            .setNegativeButton(NO){ _, _ ->}
            .setPositiveButton(YES) { _, _ ->
                deleteNews()
                Toast.makeText(activity, DELETE_SUCCESS,Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()
            }
            .create()

    private fun deleteNews(){
        //TODO: Добавить обработку ошибок от сервера
        val accessToken = getTokens.execute().accessToken
        val newsIdAndAccess = NewsIdAndAccess(id = newsId, accessToken)
        CoroutineScope(Dispatchers.Main).launch {
            deleteNews.execute(newsIdAndAccess)
        }
    }

    companion object {
        const val TAG = QUESTION
    }
}