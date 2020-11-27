package com.mirza.tugasinterview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBeli.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnBeli -> {
                val etSaldo = et_saldo.text.toString()
                val etPin = et_pin.text.toString()

                if (etSaldo.isEmpty()) {
                    et_saldo.error = "Saldo harus diisi"
                } else if(etSaldo.toInt() < 5000) {
                    et_saldo.error = "Pengisian saldo minimal 5000"
                } else if (etSaldo.toInt() > 10_000_000) {
                    et_saldo.error = "Pengisian saldo maksimal 10.000.000"
                } else if (etPin.isEmpty()) {
                    et_pin.error = "Pin harus diisi"
                } else if (etPin.length < 6) {
                    et_pin.error = "Pin minimal 6 karakter"
                } else {
                    val intent = Intent(this, PulsaActivity::class.java)
                    intent.putExtra("saldo", etSaldo)
                    intent.putExtra("pin", etPin)
                    startActivity(intent)
                }
            }
        }
    }
}