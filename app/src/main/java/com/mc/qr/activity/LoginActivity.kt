package com.mc.qr.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import cn.pedant.SweetAlert.SweetAlertDialog
import com.mc.qr.R
import com.mc.qr.databinding.ActivityLoginBinding
import com.mc.qr.model.AuthViewModel
import com.mc.qr.retrofit.response.ResponseLogin
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        binding.btnlogin.setOnClickListener(this)

        authViewModel.responseLogin.observe(this, Observer {
            obtenerDatosLogin(it!!)
        })
    }

    private fun obtenerDatosLogin(responseLogin: ResponseLogin) {
        if (responseLogin.mensaje != "Error de autenticación: usuario o contraseña incorrecto!") {

           lifecycleScope.launch {
               authViewModel.responseLogin.value?.token
               authViewModel.responseLogin.value?.id
           }

            val token: String = responseLogin.token
            val nombre: String = responseLogin.nombre
            val ad_user: Long = responseLogin.id


            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("nombreusuario", nombre)
            intent.putExtra("token", token)
            intent.putExtra("ad_user", ad_user)
            startActivity(intent)

        } else {
            // startActivity(Intent(applicationContext, LoginActivity::class.java))

            Log.i("myTag", "validacion negativa");
            //  SweetAlertDialog(this@LoginActivity, SweetAlertDialog.ERROR_TYPE)
            //   .setTitleText("ERROR")
            //    .setContentText("Nombre de usuario y/o contraseña incorrectos")
            //    .show()
        }
        binding.btnlogin.isEnabled = true
    }




    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btnlogin -> autenticarUsuario()
        }
    }

    private fun autenticarUsuario() {
        binding.btnlogin.isEnabled = false

        var okLogin = true

        if (binding.etusuario.text.toString().trim().isEmpty()) {
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            okLogin = false
            SweetAlertDialog(this@LoginActivity, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("ERROR")
                .setContentText("Falta el nombre de usuario y la contraseña")
                .show()
        } else if (binding.etpassword.text.toString().trim().isEmpty()) {
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            okLogin = false
            SweetAlertDialog(this@LoginActivity, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("ERROR")
                .setContentText("Falta Contraseña")
                .show()
        }
        if (okLogin) {
            authViewModel.autenticarUsuario(
                binding.etusuario.text.toString(),
                binding.etpassword.text.toString()
            )
        } else {
            binding.btnlogin.isEnabled = true
            SweetAlertDialog(this@LoginActivity, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("ERROR")
                .setContentText("Usuario y/o contraseña incorrectos")
                .show()
        }
    }


}