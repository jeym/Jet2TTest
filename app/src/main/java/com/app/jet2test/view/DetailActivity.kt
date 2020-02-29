package com.app.jet2test.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.jet2test.R
import com.app.jet2test.model.EmpDataModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val emp = intent.getParcelableExtra<EmpDataModel>("emp")

        Glide.with(this)
            ?.load(emp.profileImage)
            ?.placeholder(R.mipmap.ic_launcher)
            ?.apply(
                RequestOptions.overrideOf(
                    resources.getDimension(R.dimen.image_width).toInt(),
                    resources.getDimension(R.dimen.image_height).toInt()
                )
            )
            ?.into(empImage)

        empName.text=  "Name: "+emp.employeeName
        empAge.text=  "Age: "+emp.employeeAge
        empSalary.text=  "Salary: "+emp.employeeSalary

    }

}