package com.mujur.e_lumapp.core.di

import android.content.Context
import androidx.room.Room
import com.mujur.e_lumapp.core.data.source.local.room.MatakuliahDao
import com.mujur.e_lumapp.core.data.source.local.room.MatakuliahDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MatakuliahDatabase =
        Room.databaseBuilder(
            context,
            MatakuliahDatabase::class.java, "Matakuliah.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: MatakuliahDatabase): MatakuliahDao = database.matakuliahDao()
}