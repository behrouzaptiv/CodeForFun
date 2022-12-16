package com.aptiv.fika.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "fika",
    foreignKeys = [ForeignKey(
        entity = Organiser::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("organiserId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Fika(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "organiserId") val organiserId: Int,
    @ColumnInfo(name = "date") val date: Date)



