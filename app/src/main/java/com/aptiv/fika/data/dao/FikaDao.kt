package com.aptiv.fika.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aptiv.fika.data.entity.Fika
import kotlinx.coroutines.flow.Flow

@Dao
interface FikaDao {

    @Query("SELECT * FROM fika")
    fun getAll(): Flow<List<Fika>>

    @Query("SELECT * FROM fika WHERE id IN (:fikaIds)")
    fun loadAllByIds(fikaIds: IntArray): Flow<List<Fika>>

    @Query("SELECT * FROM fika WHERE organiserId = :first")
    suspend  fun findByOrganiserId(first: String): Fika

    @Query("SELECT * FROM fika WHERE date LIKE :first")
    suspend fun findByDate(first: Long): Fika

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg fikas: Fika) : List<Long>

    @Delete
    suspend fun delete(fika: Fika) : Int
}