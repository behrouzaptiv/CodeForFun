package com.aptiv.fika.data.di

import android.content.Context
import androidx.room.Room
import com.aptiv.fika.data.dao.FikaDao
import com.aptiv.fika.data.dao.OrganizerDao
import com.aptiv.fika.data.database.FikaDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FikaDataBase {
        return Room.databaseBuilder(
            context,
            FikaDataBase::class.java,
            FikaDataBase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDao(database: FikaDataBase): FikaDao {
        return database.getFikaDao()
    }

    @Provides
    fun provideOrganiserDao(database: FikaDataBase): OrganizerDao {
        return database.getOrganiserDao()
    }

}