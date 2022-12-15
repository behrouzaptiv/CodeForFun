package com.aptiv.fika.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FikaDao {
    @Query("SELECT * FROM fika")
    fun getAll(): List<Fika>

    @Query("SELECT * FROM fika WHERE id IN (:fikaIds)")
    fun loadAllByIds(fikaIds: IntArray): List<Fika>

    @Query("SELECT * FROM fika WHERE organiserId LIKE :first")
    fun findByOrganiserId(first: String): Fika

    @Query("SELECT * FROM fika WHERE date LIKE :first")
    fun findByDate(first: Long): Fika

    @Insert
    fun insertAll(vararg fikas: Fika)

    @Delete
    fun delete(fika: Fika)
}