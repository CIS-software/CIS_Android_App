package first.android.cis.ui.news.openedNews

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import first.android.cis.R
import first.android.cis.databinding.FragmentNewsBinding

class OpenedNews : Fragment() {
    private val openHeading: OpenedNewsViewModel by activityViewModels()

    companion object {
        fun newInstance() = OpenedNews()
    }

    private lateinit var viewModel: OpenedNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.opened_news_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OpenedNewsViewModel::class.java)
        // TODO: Use the ViewModel (это вообще нужно?)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        openHeading.openedHeading.observe(activity as LifecycleOwner,{
            // TODO: 22 минуты, нужно посмотреть что делать при обновлении данных во вью модел
        })
    }
}