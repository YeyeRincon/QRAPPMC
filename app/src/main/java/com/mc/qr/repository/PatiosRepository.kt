package com.mc.qr.repository

import androidx.lifecycle.MutableLiveData
import com.mc.qr.retrofit.DataPrintCliente
import com.mc.qr.retrofit.request.RequestPatios
import com.mc.qr.retrofit.response.ResponsePatios
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PatiosRepository {

    var consultapatios = MutableLiveData<ResponsePatios>()

    fun patiosconsulta(requestPatios: RequestPatios)
            : MutableLiveData<ResponsePatios> {

        val call: Call<ResponsePatios> = DataPrintCliente
            .retrofitService.consupatios(requestPatios)

        call.enqueue(object : Callback<ResponsePatios> {
            override fun onResponse(call: Call<ResponsePatios>, response: Response<ResponsePatios>) {
                consultapatios.value = response.body()

            }
            override fun onFailure(call: Call<ResponsePatios>, t: Throwable) {

                // Error logging in

            }
        })

        return consultapatios
    }
}