package com.gamaliel.advweek4160421086.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gamaliel.advweek4160421086.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application): AndroidViewModel(application) {

    val studentLD = MutableLiveData<Student>()
    private var queue: RequestQueue?=null
    val TAG = "volleyTag"

    fun fetch(studentID : String) {

        val url = "http://adv.jitusolution.com/student.php?id=$studentID"
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                Log.d("show_volley", it)

                val sType = object : TypeToken<Student>() {}.type
                val result = Gson().fromJson<Student>(it, sType)

                studentLD.value = result
                Log.d("show_student", it.toString())

            },
            {
                Log.d("show_volley", it.toString())

            }

        )

        stringRequest.tag =TAG
        queue?.add(stringRequest)
    }
}