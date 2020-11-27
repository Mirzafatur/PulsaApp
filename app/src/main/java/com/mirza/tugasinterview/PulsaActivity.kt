package com.mirza.tugasinterview

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pulsa.*
import kotlinx.android.synthetic.main.item_dialog.*

class PulsaActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulsa)

        btnContact.setOnClickListener(this)
        btn5k.setOnClickListener(this)
        btn10k.setOnClickListener(this)
        btn20k.setOnClickListener(this)
        btn25k.setOnClickListener(this)
        btn50k.setOnClickListener(this)
        btn100k.setOnClickListener(this)
        btn200k.setOnClickListener(this)
        btn500k.setOnClickListener(this)
        btn1jt.setOnClickListener(this)

        validate()
        initView()
        stateButton()
    }

    private fun stateButton() {
        val saldo = tv_jmlSaldo.text.toString().toInt()
        when (saldo) {
            in 5000..9_999 -> btn5k.isEnabled = true
            in 10_000..19_999 -> btn10k()
            in 20_000..24_999 -> btn20k()
            in 25_000..49_999 -> btn25k()
            in 50_000..99_999 -> btn50k()
            in 100_000..199_999 -> btn100k()
            in 200_000..499_999 -> btn200k()
            in 500_000..999_999 -> btn500k()
            else -> btn1jt()
        }
    }

    private fun initView() {
        tv_jmlSaldo.text = intent.getStringExtra("saldo")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnContact -> {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
                startActivityForResult(intent, 1)
            }
            R.id.btn5k -> {
                prepareHp()
            }
            R.id.btn10k -> {
                prepareHp()
            }
            R.id.btn20k -> {
                prepareHp()
            }
            R.id.btn25k -> {
                prepareHp()
            }
            R.id.btn50k -> {
                prepareHp()
            }
            R.id.btn100k -> {
                prepareHp()
            }
            R.id.btn200k -> {
                prepareHp()
            }
            R.id.btn500k -> {
                prepareHp()
            }
            R.id.btn1jt -> {
                prepareHp()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            var contactUri = data?.data ?: return
            var column = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)

            var result = contentResolver.query(contactUri, column, null, null, null, null)
            if (result?.moveToFirst()!!) {
                et_noHp.setText(result.getString(0))
            }
        }
    }

    private fun prepareHp() {
        val noHp = et_noHp.text.toString()
        if (noHp.isEmpty()) {
            et_noHp.error = "Nomor hp harus diisi"
        } else if (noHp.length < 11) {
            et_noHp.error = "Nomor hp terlalu pendek"
        } else if (noHp.length > 17) {
            et_noHp.error = "Nomor Hp terlalu panjang"
        } else {
            btn_pulsa_finish.isEnabled = true
            et_pulsa_kon_pin.isEnabled = true
            validate()
        }
    }

    private fun validate() {
        var times = 0

        btn_pulsa_finish.setOnClickListener {
            val confPin = et_pulsa_kon_pin.text.toString()
            val intentPin = intent.getStringExtra("pin").toString()

            if (confPin.isEmpty() && et_noHp.text.toString().isEmpty()) {
                et_pulsa_kon_pin.error = "Konfirmasi pin tidak boleh kosong"
                et_noHp.error = "No hp tidak boleh kosong"
            } else if (confPin.isEmpty()) {
                et_pulsa_kon_pin.error = "Konfirmasi pin tidak boleh kosong"
            } else if (confPin != intentPin) {
                et_pulsa_kon_pin.error = "Pin tidak cocok"
                times++
                if (times == 3) {
                    Dialog(this).apply {
                        setContentView(R.layout.item_dialog)
                        setCancelable(false)

                        imgDialog.setImageResource(R.drawable.ic_wrong)
                        tv_result.setText("Anda salah memasukkan pin 3 kali!")
                        btn_dialog_finish.setText("Reset Pin")
                        btn_dialog_finish.setOnClickListener {
                            finish()
                            Toast.makeText(applicationContext, "Reset Pin anda", Toast.LENGTH_SHORT).show()
                        }
                    }.show()
                }
            } else {
                Dialog(this).apply {
                    setContentView(R.layout.item_dialog)
                    setCancelable(false)

                    tv_result.setText("Selamat Pembayaran anda berhasil!")
                    btn_dialog_finish.setOnClickListener {
                        startActivity(intent)
                        finish()
                    }
                }.show()
            }
        }
    }

    private fun btn1jt() {
        btn1jt.isEnabled = true
        btn500k.isEnabled = true
        btn200k.isEnabled = true
        btn100k.isEnabled = true
        btn50k.isEnabled = true
        btn25k.isEnabled = true
        btn20k.isEnabled = true
        btn10k.isEnabled = true
        btn5k.isEnabled = true
    }

    private fun btn500k() {
        btn500k.isEnabled = true
        btn200k.isEnabled = true
        btn100k.isEnabled = true
        btn50k.isEnabled = true
        btn25k.isEnabled = true
        btn20k.isEnabled = true
        btn10k.isEnabled = true
        btn5k.isEnabled = true
    }

    private fun btn200k() {
        btn200k.isEnabled = true
        btn100k.isEnabled = true
        btn50k.isEnabled = true
        btn25k.isEnabled = true
        btn20k.isEnabled = true
        btn10k.isEnabled = true
        btn5k.isEnabled = true
    }

    private fun btn100k() {
        btn100k.isEnabled = true
        btn50k.isEnabled = true
        btn25k.isEnabled = true
        btn20k.isEnabled = true
        btn10k.isEnabled = true
        btn5k.isEnabled = true
    }

    private fun btn50k() {
        btn50k.isEnabled = true
        btn25k.isEnabled = true
        btn20k.isEnabled = true
        btn10k.isEnabled = true
        btn5k.isEnabled = true
    }

    private fun btn25k() {
        btn25k.isEnabled = true
        btn20k.isEnabled = true
        btn10k.isEnabled = true
        btn5k.isEnabled = true
    }

    private fun btn20k() {
        btn20k.isEnabled = true
        btn10k.isEnabled = true
        btn5k.isEnabled = true
    }

    private fun btn10k() {
        btn10k.isEnabled = true
        btn5k.isEnabled = true
    }

}