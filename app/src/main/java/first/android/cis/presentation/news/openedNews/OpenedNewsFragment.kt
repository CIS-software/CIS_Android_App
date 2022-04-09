package first.android.cis.presentation.news.openedNews

import android.app.Application
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import first.android.cis.databinding.OpenedNewsFragmentBinding

class OpenedNewsFragment: Fragment() {
    private var _binding: OpenedNewsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var oppenedNewsHeading: TextView
    private lateinit var oppenedNewsDiscript: TextView
    private lateinit var oppenedTimeDate: TextView
    private lateinit var deleteNewsBTN: Button
    private lateinit var mViewModel: OpenedNewsViewModel
    private val args: OpenedNewsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = OpenedNewsFragmentBinding.inflate(inflater, container, false)
        binding.let{
            oppenedNewsHeading = it.oppenedNewsHeading
            oppenedNewsDiscript = it.oppenedNewsDiscript
            oppenedTimeDate = it.oppenedTimeDate
            deleteNewsBTN = it.deleteNewsBTN
        }
        deleteNewsBTN.setOnClickListener{
            DialogAskForDelete(args.id).show(childFragmentManager, DialogAskForDelete.TAG)
        }
        mViewModel = ViewModelProvider(this, NewsFactory(application = Application(),
            newsHeading = args.heading,
            newsDiscript = args.discription,
            newsTimeDate = args.dateTime)
        )
            .get(OpenedNewsViewModel::class.java)
        mViewModel.openedHeading.observe(activity as LifecycleOwner) {
            oppenedNewsHeading.text = it
        }
        mViewModel.openedDiscript.observe(activity as LifecycleOwner) {
            oppenedNewsDiscript.text = it
        }
        mViewModel.openedTimeDate.observe(activity as LifecycleOwner){
            oppenedTimeDate.text = it
        }
        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}