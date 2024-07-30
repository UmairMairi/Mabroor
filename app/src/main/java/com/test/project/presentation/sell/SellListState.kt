package com.test.project.presentation.sell

import com.test.project.data.room.ItemToSell


data class SellListState(
    val isLoading: Boolean = false,
    val data: ArrayList<ItemToSell>? = null,
    val error: String = ""
)