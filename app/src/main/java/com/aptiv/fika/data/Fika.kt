package com.aptiv.fika.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fika(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "organiserId") val organiserId: String?,
    @ColumnInfo(name = "date") val date: Long?)
