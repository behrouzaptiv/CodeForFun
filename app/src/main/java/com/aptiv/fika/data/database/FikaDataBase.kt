package com.aptiv.fika.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aptiv.fika.data.dao.FikaDao
import com.aptiv.fika.data.dao.OrganizerDao
import com.aptiv.fika.data.entity.Fika
import com.aptiv.fika.data.entity.Organiser

@Database(entities = [Fika::class, Organiser::class], version = 1)
@TypeConverters(Converters::class)
abstract class FikaDataBase : RoomDatabase() {

    abstract fun getFikaDao(): FikaDao

    abstract fun getOrganiserDao(): OrganizerDao

    companion object {
        const val DATABASE_NAME: String = "fika_database"
        @Volatile private var instance : FikaDataBase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FikaDataBase::class.java,
            DATABASE_NAME
        ).build()
    }
}