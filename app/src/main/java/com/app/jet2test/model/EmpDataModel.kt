package com.app.jet2test.model

import com.google.gson.annotations.SerializedName

data class EmpDataModel(@SerializedName ("id")var id : String
                        , @SerializedName("employee_name")var employeeName : String
                        , @SerializedName("employee_salary")var employeeSalary : String
                        , @SerializedName("employee_age")var employeeAge : String
                        , @SerializedName("profile_image")var profileImage : String)
