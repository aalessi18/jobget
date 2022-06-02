package com.example.jobget.module

import com.example.jobget.interfaces.UserDataRepositoryInterface
import com.example.jobget.repository.UserDataRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesUserDataRepository(implementation: UserDataRepositoryImplementation): UserDataRepositoryInterface
}