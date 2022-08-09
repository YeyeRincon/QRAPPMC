package com.mc.qr.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mc.qr.retrofit.DataPrintCliente
import com.mc.qr.retrofit.request.RequestLogin
import com.mc.qr.retrofit.response.ResponseLogin
import okhttp3.Interceptor
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {


    var loginResponse = MutableLiveData<ResponseLogin>()

    fun autenticarUsuario(requestLogin: RequestLogin)

            : MutableLiveData<ResponseLogin> {
        val call: Call<ResponseLogin> = DataPrintCliente
            .retrofitService.login(requestLogin)
        call.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                loginResponse.value = response.body()


            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("ErrorLogin", t.message.toString())
            }

        })
        return loginResponse
    }

}