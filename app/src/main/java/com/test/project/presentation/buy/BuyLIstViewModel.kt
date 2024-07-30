package com.test.project.presentation.buy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.project.common.Resource
import com.test.project.data.model.BuyDataModel
import com.test.project.domain.use_case.GetBuyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BuyLIstViewModel @Inject constructor(private val getBuyListUseCase: GetBuyListUseCase) :
    ViewModel() {

    private val _buyList = MutableStateFlow<BuyListState>(BuyListState())
    val buyList: StateFlow<BuyListState> = _buyList


    fun getBuyDetails() {
        getBuyListUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _buyList.value = BuyListState(isLoading = true)
                }
                is Resource.Error -> {
                    _buyList.value = BuyListState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _buyList.value = BuyListState(data = it.data as java.util.ArrayList<BuyDataModel>?)
                }
            }
        }.launchIn(viewModelScope)
    }


}