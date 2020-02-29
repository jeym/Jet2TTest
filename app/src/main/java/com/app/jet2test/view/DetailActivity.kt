package com.app.jet2test.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.jet2test.R
import com.app.jet2test.model.EmpDataModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val emp = intent.getParcelableExtra<EmpDataModel>("emp")
        empName.text=  emp.employeeName
    }

}