package com.example.uas201804018

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class UpdateActivity : AppCompatActivity() {
    lateinit var userDBHelper: DBHelperOrder
    lateinit var inputIdp: EditText
    lateinit var inputNama: EditText
    lateinit var inputHarga: EditText
    lateinit var inputWarna: EditText
    lateinit var idpg: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        inputIdp = findViewById(R.id.input_idpu)
        inputNama = findViewById(R.id.input_namau)
        inputHarga = findViewById(R.id.input_hargau)
        inputWarna = findViewById(R.id.input_warnau)
        userDBHelper = DBHelperOrder(this)
        val bundle =  intent.extras
        if (bundle != null){
            idpg = bundle.getString("idpk").toString()
            inputIdp.setText(bundle.getString("idpk"))
            inputNama.setText(bundle.getString("namak"))
            inputHarga.setText(bundle.getString("hargak"))
            inputWarna.setText(bundle.getString("warnak"))
        }
        userDBHelper = DBHelperOrder(this)
    }
    fun Menu(v: View){
        val exit = Intent(this, MainActivity2::class.java)
        startActivity(exit)
    }
    fun cancelData(v: View){
        val exit = Intent(this, RvDbOrder::class.java)
        startActivity(exit)
    }
    fun updateData(v: View){
        var namainn = inputNama.text.toString()
        var hargainn = inputHarga.text.toString()
        var warnainn = inputWarna.text.toString()
        var idpinn = idpg
        userDBHelper.updateData(idpinn, namainn, hargainn, warnainn )
        val exit = Intent(this, RvDbOrder::class.java)
        startActivity(exit)
    }
}