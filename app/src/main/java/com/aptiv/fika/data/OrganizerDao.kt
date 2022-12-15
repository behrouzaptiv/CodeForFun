package com.aptiv.fika.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrganizerDao {
    @Query("SELECT * FROM organiser")
    fun getAll(): List<Organiser>

    @Query("SELECT * FROM organiser WHERE organiserId IN (:organiserIds)")
    fun loadAllByIds(organiserIds: IntArray): List<Organiser>

    @Query("SELECT * FROM organiser WHERE organiserId LIKE :first")
    fun findByOrganiserId(first: String): Organiser

    @Query("SELECT * FROM organiser WHERE name LIKE :first")
    fun findByName(first: String): Organiser

    @Insert
    fun insertAll(vararg organisers: Organiser)

    @Delete
    fun delete(organiser: Organiser)
}