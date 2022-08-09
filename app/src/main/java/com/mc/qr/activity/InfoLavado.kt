package com.mc.qr.activity

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mc.qr.R
import java.text.SimpleDateFormat
import java.util.*

class InfoLavado : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infoscan)

        val bundle = intent.extras
        val dato = bundle?.getString("placascaneada")
        val placatext: TextView = findViewById(R.id.txtInfoPlaca)
        placatext.setText(dato)

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val infofecha = simpleDateFormat.format(Date())
        val fechalogueo:TextView = findViewById(R.id.txtInfoFecHorLav)
        fechalogueo.setText(infofecha)
    }
}