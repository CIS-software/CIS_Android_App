package first.android.cis.ui.news.openedNews

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import first.android.cis.R
import first.android.cis.databinding.FragmentNewsBinding

class OpenedNewsFragment : Fragment() {
    private val openHeading: OpenedNewsViewModel by activityViewModels()
    lateinit var binding: FragmentO
    companion object {
        fun newInstance() = 
    }

    private lateinit var viewModel: OpenedNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.opened_news_fragment, container, false)

        openHeading.openedHeading.observe(viewLifecycleOwner,{
            binding.
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OpenedNewsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}