package com.example.gelirgideruygulamasi

import android.content.ContentValues

class Fiyatlardao {

    fun tumKayitlar(vt: VeritabaniYardimcisi) : ArrayList<Fiyatlar> {

        val db = vt.writableDatabase
        val fiyatlarListe = ArrayList<Fiyatlar>()
        val c = db.rawQuery("SELECT * FROM fiyatlar",null)

        while (c.moveToNext()) {

            val fiyat = Fiyatlar(c.getInt(c.getColumnIndexOrThrow("urun_id")),c.getString(c.getColumnIndexOrThrow("urun_ad")),c.getInt(c.getColumnIndexOrThrow("urun_alis")),c.getInt(c.getColumnIndexOrThrow("urun_satis")))

            fiyatlarListe.add(fiyat)

        }

        return fiyatlarListe

    }


    fun kayitSil(vt : VeritabaniYardimcisi, urun_id : Int) {

        val db = vt.writableDatabase
        db.delete("fiyatlar","urun_id=?", arrayOf(urun_id.toString()))
        db.close()

    }

    fun kayitEkle(vt : VeritabaniYardimcisi, urun_ad : String, urun_alis : Int, urun_satis : Int) {
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("urun_ad",urun_ad)
        values.put("urun_alis",urun_alis)
        values.put("urun_satis",urun_satis)
        db.insertOrThrow("fiyatlar",null,values)
        db.close()

    }

    fun kayitGuncelle(vt : VeritabaniYardimcisi, urun_id: Int ,urun_ad : String, urun_alis : Int, urun_satis : Int) {
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("urun_ad",urun_ad)
        values.put("urun_alis",urun_alis)
        values.put("urun_satis",urun_satis)

        db.update("fiyatlar",values,"urun_id=?", arrayOf(urun_id.toString()))
        db.close()

    }
}