package com.app.jet2test.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.jet2test.R
import com.app.jet2test.adapters.EmpAdapter
import com.app.jet2test.model.EmpDataModel
import com.app.jet2test.model.EmpModel
import com.app.jet2test.viewmodel.EmpViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var empViewModel : EmpViewModel ?= null
    private var empAdapter : EmpAdapter ?= null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var listEmp = mutableListOf<EmpDataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        empViewModel=ViewModelProviders.of(this).get(EmpViewModel::class.java)
        empViewModel?.getEmpData()?.observe(this@MainActivity, Observer {
            Log.d("data","data"+it.empList.size)
            setUpRecylerView(it.empList)

        })
    }

    fun setUpRecylerView(listEmp : MutableList<EmpDataModel>){
        //Setting up recylerview with adpater
        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        rvEmpData.layoutManager = linearLayoutManager
        empAdapter = EmpAdapter(this@MainActivity,listEmp)
        rvEmpData.adapter = empAdapter
    }
}
