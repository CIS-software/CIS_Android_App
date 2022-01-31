package first.android.cis.ui.news.openedNews

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import first.android.cis.R


class OpenedNews: Fragment() {
    private lateinit var mViewModel: OpenedNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.opened_news_fragment, container, false)
        val headingView: TextView = root.findViewById(R.id.oppenedNewsHeading)
        val discriptionView: TextView = root.findViewById(R.id.oppenedNewsDiscript)
        val args: OpenedNewsArgs by navArgs()
        val newsHeading = args.heading
        val newsDiscript = args.discription
        mViewModel = ViewModelProvider(this, NewsFactory(application = Application(),
            newsHeading = newsHeading,
            newsDiscript = newsDiscript))
            .get(OpenedNewsViewModel::class.java)

        mViewModel.openedHeading.observe(activity as LifecycleOwner,{
            headingView.text = it
        })
        mViewModel.openedDiscript.observe(activity as LifecycleOwner,{
            discriptionView.text = it
        })
        return root
    }

    companion object {
        fun newInstance() = OpenedNews()
    }
}