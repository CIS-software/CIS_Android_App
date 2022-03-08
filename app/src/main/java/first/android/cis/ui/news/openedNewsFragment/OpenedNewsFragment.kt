package first.android.cis.ui.news.openedNewsFragment

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import first.android.cis.R
import first.android.cis.network.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OpenedNewsFragment: Fragment() {
    private lateinit var mViewModel: OpenedNewsViewModel
    private val args: OpenedNewsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.opened_news_fragment, container, false)
        val headingView: TextView = root.findViewById(R.id.oppenedNewsHeading)
        val discriptionView: TextView = root.findViewById(R.id.oppenedNewsDiscript)
        val newsHeading = args.heading
        val newsDiscript = args.discription
        initDeleteButton(root)
        mViewModel = ViewModelProvider(this, NewsFactory(application = Application(),
            newsHeading = newsHeading,
            newsDiscript = newsDiscript))
            .get(OpenedNewsViewModel::class.java)
        mViewModel.openedHeading.observe(activity as LifecycleOwner) {
            headingView.text = it
        }
        mViewModel.openedDiscript.observe(activity as LifecycleOwner) {
            discriptionView.text = it
        }
        return root
    }

    private fun initDeleteButton(root: View) {
        val deleteBtn: Button = root.findViewById(R.id.deleteNewsBTN)
        deleteBtn.setOnClickListener{
            DialogAskForDelete(args.id).show(childFragmentManager, DialogAskForDelete.TAG)
        }
    }

    companion object {
        fun newInstance() = OpenedNewsFragment()
    }
}