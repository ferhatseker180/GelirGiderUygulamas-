package com.example.gelirgideruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detay.*
import kotlinx.android.synthetic.main.activity_gelir_gider_kayit.*

class DetayActivity : AppCompatActivity() {
    private lateinit var fiyat : Fiyatlar
    private lateinit var vt : VeritabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)

        vt = VeritabaniYardimcisi(this)
        toolbar2.title = "Gelir Gider Detay"
        setSupportActionBar(toolbar2)

        fiyat = intent.getSerializableExtra("etiket") as Fiyatlar
        editTextTextUrun2.setText(fiyat.urun_ad)
        editTextUrunAlis2.setText(fiyat.urun_alis.toString())
        editTextUrunSatis2.setText(fiyat.urun_satis.toString())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.action_sil -> {

                Snackbar.make(toolbar2,"Silinsin Mi?",Snackbar.LENGTH_LONG)
                    .setAction("EVET") {

                        Fiyatlardao().kayitSil(vt,fiyat.urun_id)
                        startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                        finish()
                    }.show()


                return true
            }
            R.id.action_duzenle -> {
                val urun_ad = editTextTextUrun2.text.toString().trim()
                val urun_alis = editTextUrunAlis2.text.toString().trim()
                val urun_satis = editTextUrunSatis2.text.toString().trim()

                if (TextUtils.isEmpty(urun_ad)) {
                    Snackbar.make(toolbar2,"Ürün Adı Giriniz",Snackbar.LENGTH_SHORT).show()
                    return false // Boşsa buton çalışmasın diye yazıldı.
                }

                if(TextUtils.isEmpty(urun_alis)) {
                    Snackbar.make(toolbar2,"Ürün Alış Fiyatı Giriniz",Snackbar.LENGTH_SHORT).show()
                    return false
                }

                if(TextUtils.isEmpty(urun_satis)) {
                    Snackbar.make(toolbar2,"Ürün Satış Fiyatı Giriniz",Snackbar.LENGTH_SHORT).show()
                    return false
                }
                Fiyatlardao().kayitGuncelle(vt,fiyat.urun_id,urun_ad,urun_alis.toInt(),urun_satis.toInt())


                startActivity(Intent(this@DetayActivity,MainActivity::class.java))

                finish()
                return true
            }
            else -> return false
        }

    }
}