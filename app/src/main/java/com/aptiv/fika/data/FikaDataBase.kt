package com.aptiv.fika.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Fika::class, Organiser::class], version = 1)
abstract class FikaDataBase : RoomDatabase() {
    abstract fun fikaDao(): FikaDao
    abstract fun organiserDao(): OrganizerDao
}