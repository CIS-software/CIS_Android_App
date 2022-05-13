package first.android.cis.presentation.news.openedNews

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import first.android.cis.databinding.OpenedNewsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OpenedNewsFragment: Fragment() {
    private var _binding: OpenedNewsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var oppenedNewsHeading: TextView
    private lateinit var oppenedNewsDiscript: TextView
    private lateinit var oppenedTimeDate: TextView
    private lateinit var deleteNewsBTN: Button
    private val openedNewsViewModel: OpenedNewsViewModel by viewModel<OpenedNewsViewModel>()
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
        openedNewsViewModel.setData(heading = args.heading, discription = args.discription, timeData = args.dateTime)

        openedNewsViewModel.openedHeading.observe(activity as LifecycleOwner) {
            oppenedNewsHeading.text = it
        }
        openedNewsViewModel.openedDiscript.observe(activity as LifecycleOwner) {
            oppenedNewsDiscript.text = it
        }
        openedNewsViewModel.openedTimeDate.observe(activity as LifecycleOwner){
            oppenedTimeDate.text = it
        }
        openedNewsViewModel.deleteResponse.observe(viewLifecycleOwner){ response ->
            if (response.isSuccessful){
                Toast.makeText(activity, "Запись удалена",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireActivity(), "Ошибка подключения!", Toast.LENGTH_LONG).show()
            }
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