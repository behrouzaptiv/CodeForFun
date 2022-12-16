package com.aptiv.fika.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.aptiv.fika.data.entity.Organiser
import com.aptiv.fika.data.entity.OrganiserAndFika

@Dao
interface OrganizerDao {
 @Query("SELECT * FROM organiser")
 fun getAll(): List<Organiser>

 @Query("SELECT * FROM organiser WHERE id IN (:organiserIds)")
 suspend fun loadAllByIds(organiserIds: IntArray): List<Organiser>

 @Query("SELECT * FROM organiser WHERE id LIKE :id")
 suspend fun findByOrganiserId(id: Int): Organiser

 @Query("SELECT * FROM organiser WHERE name LIKE :first")
 suspend fun findByName(first: String): Organiser

 @Insert
 suspend fun insertAll(vararg organisers: Organiser) :  List<Long>

 @Delete
 suspend fun delete(organiser: Organiser) : Int

 @Transaction
 @Query("SELECT * FROM organiser")
 suspend fun getAllFika(): List<OrganiserAndFika>

 @Transaction
 @Query("SELECT * FROM organiser WHERE id = :id")
 suspend fun getFikaByOrganiserId(id: String): OrganiserAndFika



}