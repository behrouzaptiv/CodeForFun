package com.aptiv.fika.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Organiser(
    @PrimaryKey val organiserId: Int,
    @ColumnInfo(name = "name") val name: String?)
