package com.mc.qr.retrofit

import com.mc.qr.retrofit.request.RequestLogin
import com.mc.qr.retrofit.request.RequestPatios
import com.mc.qr.retrofit.response.ResponseLogin
import com.mc.qr.retrofit.response.ResponsePatios
import retrofit2.Call
import retrofit2.http.*


interface DataPrintService {

    @POST("/api/login")
    fun login(@Body requestLogin: RequestLogin): Call<ResponseLogin>

    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET("/api/patios/")
    fun consupatios(@Body requestPatios: RequestPatios): Call<ResponsePatios>

    // get all users
    //  @GET("/api/patios/")
    //   fun consupatios(
    //     @Header("Authorization") token: String = "$token": Call<ResponsePatios>

}