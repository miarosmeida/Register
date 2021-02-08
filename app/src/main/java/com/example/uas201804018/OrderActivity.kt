package com.example.uas201804018

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class OrderActivity : AppCompatActivity() {
    lateinit var userDBHelper:DBHelperOrder
    lateinit var inputIdp: EditText
    lateinit var inputNama: EditText
    lateinit var inputHarga: EditText
    lateinit var inputWarna: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        inputIdp = findViewById(R.id.input_idp)
        inputNama = findViewById(R.id.input_nama)
        inputHarga = findViewById(R.id.input_harga)
        inputWarna = findViewById(R.id.input_warna)
        userDBHelper = DBHelperOrder(this)
    }
    fun Back(v: View){
        var kembali = Intent(this, MainActivity::class.java)
        startActivity(kembali)
    }
    fun addData(v: View){
        var idpin = inputIdp.text.toString()
        var namain = inputNama.text.toString()
        var hargain = inputHarga.text.toString()
        var warnain = inputWarna.text.toString()
        userDBHelper.insertData(idpin, namain, hargain, warnain)
        inputIdp.setText("")
        inputNama.setText("")
        inputHarga.setText("")
        inputWarna.setText("")
    }
    fun showAll(v: View){
//        var pindah = Intent(this, MainActivity2::class.java)
        var pindah = Intent(this, RvDbOrder::class.java)
        startActivity(pindah)
    }
}