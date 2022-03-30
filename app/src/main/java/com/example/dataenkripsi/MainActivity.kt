package com.example.dataenkripsi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // NAMA : RIFKI ALDIANSYAH
        // UMUR : 18TAHUN
        // KELAS : MOBILE
        val tempatData = getPreferences(Context.MODE_PRIVATE)

        idbtn_simpan.setOnClickListener {
            var name = idedt_name.text.toString()

            /* jika editext kosong ini akan di eksekusi */
            if (name.isEmpty()){
                idedt_name.error = "error bro"
                Toast.makeText(
                    this,
                    "Masukkan Nama Anda",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            /**/

            val simpanNama = enkripsiData(name)

            val simpanData = tempatData.edit()
            simpanData.putString("Nama", simpanNama)
            simpanData.apply()

            Toast.makeText(
                this,
                "Nama Anda Tersimpan",
                Toast.LENGTH_SHORT
            ).show()

            kosongText()
        }

        idbtn_data.setOnClickListener {
            var panggilNama = tempatData.getString("Nama", null)
            val panggilHasil = deskripsiData(panggilNama.toString())
            idtv_hasil.text = "$panggilHasil \nEnkripsi : $panggilNama"
        }

    }

    //total ada 3 function

    private fun enkripsiData(namaEnkripsi: String): String{
        val enkripsi = Base64.encode(
            namaEnkripsi.toByteArray(),
            Base64.DEFAULT
        )
        return String(enkripsi)
    }

    private fun kosongText(){
        idedt_name.setText("")
        idtv_hasil.setText("")
    }

    private fun deskripsiData(namaEnkripsi: String): String{
        val enkripsi = Base64.decode(
            namaEnkripsi.toByteArray(),
            Base64.DEFAULT
        )
        return String(enkripsi)
    }
}