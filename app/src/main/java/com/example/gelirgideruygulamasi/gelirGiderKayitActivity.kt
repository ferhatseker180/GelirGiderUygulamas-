package com.example.gelirgideruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_gelir_gider_kayit.*

class gelirGiderKayitActivity : AppCompatActivity() {
    private lateinit var vt : VeritabaniYardimcisi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gelir_gider_kayit)

        vt = VeritabaniYardimcisi(this)

        toolbarGelirGiderKayit.title = "Gelir Gider Kayıt"

        buttonKaydet.setOnClickListener {

            val urun_ad = editTextTextUrun.text.toString().trim() // trim yanlışlıkla girilen boşlukları siler
            val urun_alis = editTextUrunAlis.text.toString().trim()
            val urun_satis = editTextUrunSatis.text.toString().trim()

            if (TextUtils.isEmpty(urun_ad)) {
                Snackbar.make(toolbarGelirGiderKayit,"Ürün Adı Giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener // Boşsa buton çalışmasın diye yazıldı.
            }

            if(TextUtils.isEmpty(urun_alis)) {
                Snackbar.make(toolbarGelirGiderKayit,"Ürün Alış Fiyatı Giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(urun_satis)) {
                Snackbar.make(toolbarGelirGiderKayit,"Ürün Satış Fiyatı Giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Fiyatlardao().kayitEkle(vt,urun_ad, urun_alis.toInt(),urun_satis.toInt())

            startActivity(Intent(this@gelirGiderKayitActivity,MainActivity::class.java))
            finish()
        }
    }
}