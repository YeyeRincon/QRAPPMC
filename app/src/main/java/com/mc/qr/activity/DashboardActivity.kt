package com.mc.qr.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.mc.qr.R
import java.text.SimpleDateFormat
import java.util.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        infoadicionaldash()
        btnmenuescaneo()
        btnmenusalidasegura()
    }

    //Información Adicional
    fun infoadicionaldash() {

        SweetAlertDialog(this@DashboardActivity, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("BIENVENIDO")
            .show()

        //fecha y hora de acceso
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val infofecha = simpleDateFormat.format(Date())
        val fechalogueo: TextView = findViewById(R.id.txtinfologueo)
        fechalogueo.setText(infofecha)

        //envio del nombre del usuario
        val bundle = intent.extras
        val nomuser = bundle?.getString("nombreusuario")
        val txtnomuser: TextView = findViewById(R.id.txtnomuser)
        txtnomuser.setText(nomuser)
    }

    //Boton que envía al activity de scaneo
    fun btnmenuescaneo() {
        val BtnMenuEscan: Button = findViewById(R.id.btnmenuscan)
        BtnMenuEscan.setOnClickListener {
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)
        }
    }

    //Botón de salida segura
    fun btnmenusalidasegura() {
        val BtnMenuSalida: Button = findViewById(R.id.btnmenuSalida)
        BtnMenuSalida.setOnClickListener {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Salida Segura")
                .setContentText("¿Desea salir de la aplicación?")
                .setCancelText("No, Cancelar!").setConfirmText("Sí, Cerrar")
                .showCancelButton(true)
                .setCancelClickListener { sDialog: SweetAlertDialog ->
                    sDialog.dismissWithAnimation()
                    SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Operación cancelada")
                        .show()
                }.setConfirmClickListener { sweetAlertDialog: SweetAlertDialog ->
                    sweetAlertDialog.dismissWithAnimation()
                    System.exit(0)
                }.show()
        }
    }


}