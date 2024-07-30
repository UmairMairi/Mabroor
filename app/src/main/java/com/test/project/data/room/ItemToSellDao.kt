package com.test.project.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ItemToSellDao {
    @Insert
    suspend fun insertItem(item: List<ItemToSell>)

    @Query("SELECT * FROM ItemToSell")
    suspend fun getAllItems(): List<ItemToSell>

    @Query("SELECT COUNT(*) FROM ItemToSell")
    suspend fun getRowCount(): Int

    @Transaction
    suspend fun isDatabaseEmpty(): Boolean {
        return getRowCount() == 0
    }
}
