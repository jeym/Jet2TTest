package com.app.jet2test.api

import com.app.jet2test.model.EmpModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("employees")
    fun getEmpData() : Call<EmpModel>
}