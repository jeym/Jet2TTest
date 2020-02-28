package com.app.jet2test.di

import com.app.jet2test.repositories.EmpRepository
import com.app.jet2test.viewmodel.EmpViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(RetrofitModule::class, RepositoryModule::class))
interface ApplicationComponent {
    fun injectRepository(empViewModel: EmpViewModel)
    fun injectRetrofit(empRepository: EmpRepository)
}