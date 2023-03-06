package com.example.gelirgideruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var fiyatlarListe:ArrayList<Fiyatlar>
    private lateinit var adapter:FiyatlarAdapter
    private lateinit var vt : VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Gelir Gider Uygulaması"

        setSupportActionBar(toolbar)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

       vt = VeritabaniYardimcisi(this)

        fiyatlarListe = Fiyatlardao().tumKayitlar(vt)

        adapter = FiyatlarAdapter(this,fiyatlarListe)
        recyclerView.adapter = adapter

        var toplam = 0
        for (f in fiyatlarListe) {

            toplam = toplam +(f.urun_satis - f.urun_alis)
        }
        if (toplam!=0) {

            toolbar.subtitle = "Kar : ${toplam}$"
            Snackbar.make(toolbar,"Kazançlısınız, Tebrikler!",Snackbar.LENGTH_SHORT).show()

        }
        if (toplam==0) {

            toolbar.subtitle = "Kar : ${toplam}$"
            Snackbar.make(toolbar,"Avantajda Veya Zararda Değilsiniz!",Snackbar.LENGTH_SHORT).show()
        }
        if (toplam<0) {
            toolbar.subtitle = "Kar : ${toplam}$"
            Snackbar.make(toolbar,"Zarar Ediyorsunuz, Gelir Gider Dengesini Kontrol Ediniz!",Snackbar.LENGTH_SHORT).show()

        }


        fab.setOnClickListener {

            startActivity(Intent(this@MainActivity,gelirGiderKayitActivity::class.java))

        }
    }

    override fun onBackPressed() {
        // Ana Ekranda geri tuşuna basıldığında uygulamayı kapatacak ve tekrar tekrar anasayfayı yüklemeyecek.

        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}