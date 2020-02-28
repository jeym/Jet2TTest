package com.app.jet2test.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.jet2test.MyApplication
import com.app.jet2test.model.EmpModel
import com.app.jet2test.repositories.EmpRepository
import javax.inject.Inject

class EmpViewModel(application: Application) : AndroidViewModel(application){


    init {
        (application as MyApplication).getApplicationComponent().injectRepository(this)
    }

    @Inject
    lateinit var empRepository : EmpRepository

    fun getEmpData() : MutableLiveData<EmpModel>{
        return empRepository.getEmpData()!!
    }
}