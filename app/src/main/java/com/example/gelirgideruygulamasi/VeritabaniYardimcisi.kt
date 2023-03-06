package com.example.gelirgideruygulamasi

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeritabaniYardimcisi(context: Context) : SQLiteOpenHelper(context,"fiyatlar.sqlite",null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {

        p0?.execSQL("CREATE TABLE fiyatlar(urun_id INTEGER PRIMARY KEY AUTOINCREMENT, urun_ad TEXT, urun_alis INTEGER, urun_satis INTEGER );")


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

        p0?.execSQL("DROP TABLE IF EXISTS fiyatlar ")
        onCreate(p0)

    }
}