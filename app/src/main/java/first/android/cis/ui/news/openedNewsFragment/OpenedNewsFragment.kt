package first.android.cis.ui.news.openedNewsFragment

import android.app.Application
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import first.android.cis.R

class OpenedNewsFragment: Fragment() {
    private lateinit var mViewModel: OpenedNewsViewModel
    private val args: OpenedNewsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.opened_news_fragment, container, false)
        val headingView: TextView = root.findViewById(R.id.oppenedNewsHeading)
        val discriptionView: TextView = root.findViewById(R.id.oppenedNewsDiscript)
        val timeDateView: TextView = root.findViewById(R.id.oppenedTimeDate)
        initDeleteButton(root)
        mViewModel = ViewModelProvider(this, NewsFactory(application = Application(),
            newsHeading = args.heading,
            newsDiscript = args.discription,
            newsTimeDate = args.dateTime))
            .get(OpenedNewsViewModel::class.java)
        mViewModel.openedHeading.observe(activity as LifecycleOwner) {
            headingView.text = it
        }
        mViewModel.openedDiscript.observe(activity as LifecycleOwner) {
            discriptionView.text = it
        }
        mViewModel.openedTimeDate.observe(activity as LifecycleOwner){
            timeDateView.text = it
        }
        val navView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navView.visibility = View.GONE
        return root
    }

    private fun initDeleteButton(root: View) {
        val deleteBtn: Button = root.findViewById(R.id.deleteNewsBTN)
        deleteBtn.setOnClickListener{
            DialogAskForDelete(args.id).show(childFragmentManager, DialogAskForDelete.TAG)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance() = OpenedNewsFragment()
    }

}