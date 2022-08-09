package com.mc.qr.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.mc.qr.R
import com.mc.qr.configuration.Constantes
import com.mc.qr.databinding.ActivityScanBinding
import java.net.HttpURLConnection
import java.net.URL

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // super.onCreate(savedInstanceState)
        //   setContentView(R.layout.activity_scan)
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnatras1()
        iniscan()
    }

    fun btnatras1() {
        val BtnAtrasMenu: Button = findViewById(R.id.btnAtras1)
        BtnAtrasMenu.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

    }

    fun iniscan() {
        val BtnIniScan: Button = findViewById(R.id.btniniscan)
        BtnIniScan.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setCameraId(0)
            integrator.setBeepEnabled(true)
            integrator.setBarcodeImageEnabled(true)
            integrator.setOrientationLocked(true)
            integrator.setPrompt("Ubique la placa en el lector")
            integrator.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, InfoLavado::class.java)
                intent.putExtra("placascaneada",result.contents)
                startActivity(intent)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }



}