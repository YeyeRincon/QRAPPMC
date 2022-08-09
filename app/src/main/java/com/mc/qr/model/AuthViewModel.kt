package com.mc.qr.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mc.qr.repository.AuthRepository
import com.mc.qr.retrofit.request.RequestLogin
import com.mc.qr.retrofit.response.ResponseLogin


class AuthViewModel : ViewModel() {
    var responseLogin: LiveData<ResponseLogin>

    private var repository = AuthRepository()
    init {
        responseLogin = repository.loginResponse
    }

   fun autenticarUsuario(usuario: String, password: String) {
       responseLogin = repository.autenticarUsuario(
           RequestLogin(usuario, password)
       )
    }



}