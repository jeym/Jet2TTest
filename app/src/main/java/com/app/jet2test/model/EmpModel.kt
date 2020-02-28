package com.app.jet2test.model

import com.google.gson.annotations.SerializedName

data class EmpModel(@SerializedName ("status")var status : String,
                    @SerializedName("data")var empList  : MutableList<EmpDataModel>)