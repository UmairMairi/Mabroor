package com.test.project.presentation.call

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.project.common.Resource
import com.test.project.domain.use_case.GetCallListUseCase
import com.test.project.domain.use_case.PostDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CallListViewModel @Inject constructor(
    private val callListUseCase: GetCallListUseCase,
    private val dataUseCase: PostDataUseCase
) : ViewModel() {

    private val _callList = MutableStateFlow(CallListState())
    val callList: StateFlow<CallListState> = _callList

    private val _data = MutableStateFlow(CallListState())
    val data: StateFlow<CallListState> = _data

    fun getCallList() {
        callListUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _callList.value = CallListState(isLoading = true)
                }
                is Resource.Success -> {
                    _callList.value = CallListState(data = ArrayList(it.data))
                }
                is Resource.Error -> {
                    _callList.value = CallListState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun dataUseCase() {
        callListUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _callList.value = CallListState(isLoading = true)
                }
                is Resource.Success -> {
                    _callList.value = CallListState(data = ArrayList(it.data))
                }
                is Resource.Error -> {
                    _callList.value = CallListState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

}