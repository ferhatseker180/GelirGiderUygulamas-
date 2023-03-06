package com.example.gelirgideruygulamasi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class FiyatlarAdapter(private val mContext: Context, private val fiyatlarListe : List<Fiyatlar>) : RecyclerView.Adapter<FiyatlarAdapter.CardTasarimTutucu>(){

    inner class CardTasarimTutucu(tasarim:View) : RecyclerView.ViewHolder(tasarim) {

        var cardView : CardView
        var textViewUrun : TextView
        var textViewAlis : TextView
        var textViewSatis : TextView

        init {
            cardView = tasarim.findViewById(R.id.cardView)
            textViewUrun = tasarim.findViewById(R.id.textViewUrun)
            textViewAlis = tasarim.findViewById(R.id.textViewAlis)
            textViewSatis = tasarim.findViewById(R.id.textViewSatis)



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {

        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.card_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {

       return fiyatlarListe.size

    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

        val fiyat = fiyatlarListe.get(position)
        holder.textViewUrun.text = fiyat.urun_ad
        holder.textViewAlis.text = fiyat.urun_alis.toString()
        holder.textViewSatis.text = fiyat.urun_satis.toString()

        holder.cardView.setOnClickListener {

            val intent = Intent(mContext,DetayActivity::class.java)
            intent.putExtra("etiket",fiyat)
            mContext.startActivity(intent)
        }

    }
}