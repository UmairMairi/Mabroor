
package com.test.project.data.room
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemToSell")
data class ItemToSell(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val price: Long,
    val quantity: Int,
    val type: Int
)