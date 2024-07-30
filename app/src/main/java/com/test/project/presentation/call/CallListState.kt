package com.test.project.presentation.call

import com.test.project.data.model.CallDataModel

data class CallListState(
    val isLoading: Boolean = false,
    val data: ArrayList<CallDataModel>? = null,
    val error: String = ""
)