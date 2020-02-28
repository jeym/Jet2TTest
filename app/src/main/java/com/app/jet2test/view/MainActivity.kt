package com.app.jet2test.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.jet2test.R
import com.app.jet2test.viewmodel.EmpViewModel

class MainActivity : AppCompatActivity() {


    var empViewModel : EmpViewModel ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        empViewModel=ViewModelProviders.of(this).get(EmpViewModel::class.java)
        empViewModel?.getEmpData()?.observe(this@MainActivity, Observer {
            Log.d("data","data"+it.empList.size)
        })
    }
}
