package com.mc.qr.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mc.qr.repository.PatiosRepository
import com.mc.qr.retrofit.request.RequestPatios
import com.mc.qr.retrofit.response.ResponsePatios

class PatiosViewModel: ViewModel() {

    lateinit var responsePatios: LiveData<ResponsePatios>
    private var repopatios = PatiosRepository()

    init {
        responsePatios= repopatios.consultapatios
    }

    fun patios(iduser: Int){
        responsePatios = repopatios.patiosconsulta(
            RequestPatios(iduser)
        )
    }


}