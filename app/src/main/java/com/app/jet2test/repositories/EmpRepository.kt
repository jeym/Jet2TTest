package com.app.jet2test.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.jet2test.MyApplication
import com.app.jet2test.api.ApiService
import com.app.jet2test.model.EmpDataModel
import com.app.jet2test.model.EmpModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject

class EmpRepository (application: Application) {


    private var mEmpData: MutableLiveData<EmpModel>? = MutableLiveData()
    private val isOnline : MutableLiveData<Boolean> ?= MutableLiveData()


    init {
        (application as MyApplication).getApplicationComponent().injectRetrofit(this)
    }

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var connectivityManager: ConnectivityManager

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


    fun isOnline() : MutableLiveData<Boolean>?{
        GlobalScope.launch(Dispatchers.Main) {
            // call to UI thread
            val networkCapabilities = connectivityManager?.getNetworkCapabilities(connectivityManager?.activeNetwork)
            isOnline?.value=   networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
        }
        return isOnline
    }

}