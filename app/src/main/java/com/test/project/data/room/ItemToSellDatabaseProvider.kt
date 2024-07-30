package com.test.project.data.room

import android.content.Context
import androidx.room.Room

object ItemToSellDatabaseProvider {
    private var database: ItemToSellDatabase? = null

    fun getDatabase(context: Context): ItemToSellDatabase {
        if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                ItemToSellDatabase::class.java,
                "item_to_sell_database"
            ).build()
        }
        return database as ItemToSellDatabase
    }
}
