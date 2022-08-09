package com.mc.qr.retrofit

import com.mc.qr.configuration.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DataPrintCliente {

    private  var okHttpClient=OkHttpClient.Builder()
        .connectTimeout(1,TimeUnit.MINUTES)
        .readTimeout(30,TimeUnit.MINUTES)
        .writeTimeout(15,TimeUnit.MINUTES)
        .build()

    private fun buildRetrofit() =Retrofit.Builder()
        .baseUrl(Constantes().Data_Print_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: DataPrintService by lazy{
        buildRetrofit().create(DataPrintService::class.java)
    }
}