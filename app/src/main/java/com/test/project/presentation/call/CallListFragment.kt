package com.test.project.presentation.call

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
import com.test.project.databinding.FragmentCallListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallListFragment : Fragment() {

    private val callListAdapter = CallListAdapter()
    private lateinit var  methods:CommonMethods
    lateinit var  mContext: Context

    private val viewModel: CallListViewModel by viewModels()

    private var _binding: FragmentCallListBinding? = null
    private val binding: FragmentCallListBinding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCallListBinding.inflate(inflater, container, false)
        mContext=requireContext()
        methods=CommonMethods
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.callListRecycler.apply {
            adapter = callListAdapter
        }

        if (methods.isInternetAvailable(mContext)){
            viewModel.getCallList()
        }else{
            binding.nothingFound.visibility = View.VISIBLE
            binding.nothingFound.visibility = View.VISIBLE
            Toast.makeText(mContext,mContext.resources.getString(R.string.no_Internet) , Toast.LENGTH_SHORT).show()
        }

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.data.collect { it ->
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
                    callListAdapter.setContentList(it.toMutableList())
                }


            }


        }


    }

}