package first.android.cis.presentation.news.openedNews

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import first.android.cis.data.tokens.TokensRepositoryImpl
import first.android.cis.domain.usecases.signInUp.GetTokens
import first.android.cis.network.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DialogAskForDelete(private val newsId: Int) : DialogFragment() {
    private val tokensRepository by lazy{TokensRepositoryImpl(context = requireActivity())}
    private val getTokens by lazy{ GetTokens(tokensRepository) }

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
        //TODO: Добавить обработку ошибок от сервера
        val accessToken = getTokens.execute().accessToken
        CoroutineScope(Dispatchers.Main).launch {
            Retrofit.newsApi.deleteNews(newsId, accessToken)
        }
    }

    companion object {
        const val TAG = "Вопрос"
    }
}