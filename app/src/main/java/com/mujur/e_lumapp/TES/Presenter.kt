package com.mujur.e_lumapp.TES

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Presenter (private val view: GeneralView, private val context: Context){
    fun getById(id: Int) {
        view.showLoading()
        val api = RetrofitFactory.create(context)
        val request = api.getStatusById(id)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.await()
                //2xx
                view.success(response)
            } catch (e: Exception) {
                view.error(e)
            }
            view.hideLoading()
        }
    }
}