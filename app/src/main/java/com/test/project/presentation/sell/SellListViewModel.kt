package com.test.project.presentation.sell

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.project.data.room.ItemToSell
import com.test.project.data.room.ItemToSellDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellListViewModel @Inject constructor(private val itemToSellDao: ItemToSellDao) :
    ViewModel() {

    private val _sellList = MutableStateFlow<SellListState>(SellListState())
    val sellList: StateFlow<SellListState> = _sellList

    private fun getSellList() {
        viewModelScope.launch {
            try {
                val data = itemToSellDao.getAllItems()

                _sellList.value = SellListState(data = ArrayList(data))
            } catch (e: Exception) {
                _sellList.value = SellListState(error = e.message ?: "")
            }
        }
    }

    fun addSellList() {
        viewModelScope.launch {
            try {
                val isEmptyDatabase = itemToSellDao.isDatabaseEmpty() // Check if the database is empty
                if (isEmptyDatabase) {
                    val itemsToInsert = listOf(
                        ItemToSell(name = "Table", price = 12000, quantity = 1, type = 2),
                        ItemToSell(name = "TV", price = 38000, quantity = 2, type = 2),
                        ItemToSell(name = "Iphone X", price = 150000, quantity = 1, type = 2)
                    )
                    val data = itemToSellDao.insertItem(itemsToInsert)
                    _sellList.value = SellListState(data = ArrayList(itemsToInsert))
                }else{
                    getSellList()
                }
            } catch (e: Exception) {
                _sellList.value = SellListState(error = e.message ?: "")
            }
        }
    }

}
