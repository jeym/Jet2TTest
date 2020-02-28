package com.app.jet2test.repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.jet2test.MyApplication
import com.app.jet2test.api.ApiService
import com.app.jet2test.model.EmpModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class EmpRepository (application: Application) {


    private var mEmpData: MutableLiveData<EmpModel>? = MutableLiveData()



    init {
        (application as MyApplication).getApplicationComponent().injectRetrofit(this)
    }

    @Inject
    lateinit var retrofit: Retrofit

    fun getEmpData(): MutableLiveData<EmpModel>? {
        //api call
        retrofit?.create(ApiService::class.java).getEmpData().enqueue(object : Callback<EmpModel>{
            override fun onFailure(call: Call<EmpModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<EmpModel>, response: Response<EmpModel>) {
                    mEmpData?.value = response.body()
            }

        })
        return mEmpData
    }
}