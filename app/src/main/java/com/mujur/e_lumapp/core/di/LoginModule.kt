package com.mujur.e_lumapp.core.di

import com.mujur.e_lumapp.core.data.LoginRepository
import com.mujur.e_lumapp.core.data.source.remote.network.ApiService
import com.mujur.e_lumapp.core.domain.repository.ILoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideLoginRepository(apiService: ApiService): ILoginRepository {
        return LoginRepository(apiService)
    }
}