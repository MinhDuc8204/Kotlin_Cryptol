package com.plcoding.cryptocurrencyappyt.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Name: String,
    val Phone: String,
    val Address: String,

    val UserName: String,
    val Password: String
)