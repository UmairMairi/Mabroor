package com.test.project.presentation.sell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.test.project.databinding.FragmentSellListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class SellListFragment : Fragment() {

    private val sellListAdapter = SellListAdapter()

    private var _binding: FragmentSellListBinding? = null
    val binding: FragmentSellListBinding
        get() = _binding!!

    private val viewModel: SellListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSellListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.addSellList()

        binding.sellListRecycler.apply {
            adapter = sellListAdapter
        }

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.sellList.collect { it ->
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressIndicator.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressIndicator.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressIndicator.visibility = View.GONE
                    sellListAdapter.setContentList(it.toMutableList())
                }


            }
        }


    }

}