package com.aptiv.fika.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class OrganiserAndFika(
    @Embedded
    val organiser: Organiser,
    @Relation(
        parentColumn = "id",
        entityColumn = "organiserId"
    )
    val fikaList: List<Fika>
)