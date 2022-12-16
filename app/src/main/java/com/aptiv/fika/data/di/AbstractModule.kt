package com.aptiv.fika.data.di

import com.aptiv.fika.data.LocalDataSource
import com.aptiv.fika.data.RepositoryImpl
import com.aptiv.fika.domain.repository.DataSource
import com.aptiv.fika.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindLocalSource(localSourceImpl: LocalDataSource): DataSource

}