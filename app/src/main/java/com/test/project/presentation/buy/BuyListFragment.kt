package com.test.project.presentation.buy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.test.project.R
import com.test.project.utility.CommonMethods
import com.test.project.databinding.FragmentBuyListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BuyListFragment : Fragment() {

    private val buyListAdapter = BuyListAdapter()
    private lateinit var  methods:CommonMethods
    lateinit var  mContext:Context

    private var _binding: FragmentBuyListBinding? = null
    val binding: FragmentBuyListBinding
        get() = _binding!!

    private val viewModel: BuyLIstViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuyListBinding.inflate(inflater, container, false)
        mContext=requireContext()
        methods=CommonMethods
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buyItemRecycler.apply {
            adapter = buyListAdapter
        }
        if (methods.isInternetAvailable(mContext)){
            viewModel.getBuyDetails()
        }else{
            binding.nothingFound.visibility = View.VISIBLE
            binding.nothingFound.visibility = View.VISIBLE
            Toast.makeText(requireContext(),requireContext().resources.getString(R.string.no_Internet) , Toast.LENGTH_SHORT).show()

        }


        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.buyList.collect { it ->
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressIndicator.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressIndicator.visibility = View.GONE
                    Toast.makeText(mContext, it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressIndicator.visibility = View.GONE
                    buyListAdapter.setContentList(it.toMutableList())
                }


            }
        }


//        binding.detailsBackArrow.setOnClickListener {
//            findNavController().popBackStack()
//        }


    }

}