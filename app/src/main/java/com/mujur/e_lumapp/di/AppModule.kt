package com.mujur.e_lumapp.di

import com.mujur.e_lumapp.core.domain.usecase.MatakuliahInteractor
import com.mujur.e_lumapp.core.domain.usecase.MatakuliahUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideMatakuliahUseCase(matakuliahInteractor: MatakuliahInteractor): MatakuliahUseCase
}
