package com.test.project.presentation.buy

import com.test.project.data.model.BuyDataModel

data class BuyListState(
    val isLoading: Boolean = false,
    val data: ArrayList<BuyDataModel>? = null,
    val error: String = ""
)