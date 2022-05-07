package com.mujur.e_lumapp.core.di

import com.mujur.e_lumapp.core.data.StatusRepository
import com.mujur.e_lumapp.core.data.source.remote.network.ApiStatus
import com.mujur.e_lumapp.core.domain.repository.IStatusRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class StatusModule {
    @Singleton
    @Provides
    fun provideStatusApi(retrofit: Retrofit): ApiStatus {
        return retrofit.create(ApiStatus::class.java)
    }

    @Singleton
    @Provides
    fun provideStatusRepository(apiStatus: ApiStatus): IStatusRepository {
        return StatusRepository(apiStatus)
    }
}