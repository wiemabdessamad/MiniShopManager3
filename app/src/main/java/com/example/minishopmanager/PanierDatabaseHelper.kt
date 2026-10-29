
package com.example.minishopmanager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PanierDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "MiniShop.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE panier (
                id INTEGER PRIMARY KEY,
                nom TEXT NOT NULL,
                prix REAL NOT NULL
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS panier")
        onCreate(db)
    }

    fun ajouterProduit(nom: String, prix: Double): Long {
        val db = writableDatabase

        val valeurs = ContentValues().apply {
            put("nom", nom)
            put("prix", prix)
        }

        return db.insert("panier", null, valeurs)
    }
}
