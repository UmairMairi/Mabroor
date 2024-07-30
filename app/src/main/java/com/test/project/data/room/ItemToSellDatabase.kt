package com.test.project.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemToSell::class], version = 1)
abstract class ItemToSellDatabase : RoomDatabase() {
    abstract fun itemToSellDao(): ItemToSellDao
}
