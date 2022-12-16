package com.aptiv.fika.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "organiser")
data class Organiser(
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    @ColumnInfo(name = "name")
    var name: String)
