package com.test.project.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.test.project.R
import com.test.project.databinding.FragmentCallListBinding
import com.test.project.databinding.FragmentHomePageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding: FragmentHomePageBinding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.let {
            it.callList.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homePage_to_callList
                )
            }

            it.buyList.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homePage_to_buyList
                )
            }

            it.sellList.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homePage_to_sellList
                )


            }


        }


    }

}