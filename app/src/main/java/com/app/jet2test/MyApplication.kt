package com.app.jet2test

import android.app.Application
import com.app.jet2test.di.ApplicationComponent
import com.app.jet2test.di.DaggerApplicationComponent
import com.app.jet2test.di.RepositoryModule
import com.app.jet2test.di.RetrofitModule

class MyApplication : Application(){


    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().repositoryModule(
            RepositoryModule(this)).retrofitModule(RetrofitModule(this,"http://dummy.restapiexample.com/api/v1/")).build()
    }


    fun getApplicationComponent() : ApplicationComponent {
        return applicationComponent
    }


}