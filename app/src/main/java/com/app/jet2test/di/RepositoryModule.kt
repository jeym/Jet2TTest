package com.app.jet2test.di

import android.app.Application
import com.app.jet2test.repositories.EmpRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(private val application: Application){

    @Singleton
    @Provides
    fun provideRepository() : EmpRepository{
        return EmpRepository(application)
    }
}