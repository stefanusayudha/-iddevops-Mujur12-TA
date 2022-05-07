package com.mujur.e_lumapp.core.di

import com.mujur.e_lumapp.core.data.MatakuliahRepository
import com.mujur.e_lumapp.core.domain.repository.IMatakuliahRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(matakuliahRepository: MatakuliahRepository): IMatakuliahRepository

}