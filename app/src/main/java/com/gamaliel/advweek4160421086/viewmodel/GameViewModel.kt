package com.gamaliel.advweek4160421086.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gamaliel.advweek4160421086.model.Game
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GameViewModel (application: Application): AndroidViewModel(application) {
    val gamesLD = MutableLiveData<ArrayList<Game>>()
    val gameLoadLD = MutableLiveData<Boolean>()
    val gameLoadErrorLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    fun refresh() {

        gameLoadErrorLD.value = false
        gameLoadLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/game.json"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                gameLoadLD.value = false
                Log.d("show_volley", it)

                val sType = object : TypeToken<List<Game>>() {}.type
                val result = Gson().fromJson<List<Game>>(it, sType)
                gamesLD.value = result as ArrayList<Game>?

            },
            {
                gameLoadLD.value = false
                Log.e("show_volley", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}