package com.aptiv.fika.presentation.di

import com.aptiv.fika.domain.repository.Repository
import com.aptiv.fika.domain.usecase.GetAllPerson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideGetAllPerson(repository: Repository): GetAllPerson {
        return GetAllPerson(repository)
    }
}