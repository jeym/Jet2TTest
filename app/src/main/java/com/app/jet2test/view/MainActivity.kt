package com.app.jet2test.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.jet2test.R
import com.app.jet2test.adapters.EmpAdapter
import com.app.jet2test.model.EmpDataModel
import com.app.jet2test.viewmodel.EmpViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.Comparator


class MainActivity : AppCompatActivity() {

    private var empViewModel : EmpViewModel ?= null
    private var empAdapter : EmpAdapter ?= null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var listEmp = mutableListOf<EmpDataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        empViewModel=ViewModelProviders.of(this).get(EmpViewModel::class.java)
        getEmpData()
        spSort?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2==0){
                    sort("Name",listEmp)
                }else{
                    sort("Age",listEmp)
                }
                setUpRecylerView(listEmp)
            }
        }
    }

    fun setUpRecylerView(listEmp : MutableList<EmpDataModel>){
        //Setting up recylerview with adpater
        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        rvEmpData.layoutManager = linearLayoutManager
        empAdapter = EmpAdapter(this@MainActivity,listEmp)
        rvEmpData.adapter = empAdapter
    }


    fun sort(sortBy : String, listEmp: MutableList<EmpDataModel>) : MutableList<EmpDataModel>{
        if(sortBy.equals("Name")){
            Collections.sort(listEmp, Comparator { t: EmpDataModel, t2: EmpDataModel ->
                return@Comparator t.employeeName.compareTo(t2.employeeName)
            })
        }else if(sortBy.equals("Age")){
            Collections.sort(listEmp, Comparator { t: EmpDataModel, t2: EmpDataModel ->
                return@Comparator t.employeeAge.compareTo(t2.employeeAge)
            })
        }
        return listEmp
    }



    fun getEmpData(){
        GlobalScope.launch(Dispatchers.Main) {
            // call to UI thread
            empViewModel?.isOnline()?.observe(this@MainActivity, Observer {
                it?.let {
                    if(it) {
                        empViewModel?.getEmpData()?.observe(this@MainActivity, Observer {
                            listEmp= it.empList
                            sort("Name",listEmp)
                            setUpRecylerView(listEmp)

                        })
                    }else{
                        val snackbar: Snackbar = Snackbar
                            .make(rootConstraint, "Sorry, You're Offline", Snackbar.LENGTH_LONG)
                        snackbar.show()
                    }
                }
            })
        }
    }
}
