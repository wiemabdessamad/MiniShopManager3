
package com.example.minishopmanager

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import org.json.JSONArray

class ProduitsActivity : ComponentActivity() {

    private val nomsProduits = mutableListOf<String>()
    private val prixProduits = mutableListOf<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produits)

        val listView = findViewById<ListView>(R.id.listViewProduits)

        chargerProduits()

        val listeAffichage = nomsProduits.indices.map { i ->
            "${nomsProduits[i]} — ${prixProduits[i]} DT"
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listeAffichage
        )

        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->

            val nom = nomsProduits[position]
            val prix = prixProduits[position]

            val databaseHelper = PanierDatabaseHelper(this)
            val resultat = databaseHelper.ajouterProduit(nom, prix)

            if (resultat != -1L) {
                Toast.makeText(
                    this,
                    "$nom ajouté au panier !",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Erreur lors de l'ajout du produit",
                    Toast.LENGTH_SHORT
                ).show()
            }

            databaseHelper.close()
        }

    }
    }

    private fun chargerProduits() {
        val json = assets.open("produits.json")
            .bufferedReader()
            .use { it.readText() }

        val tableau = JSONArray(json)

        for (i in 0 until tableau.length()) {
            val produit = tableau.getJSONObject(i)

            nomsProduits.add(produit.getString("nom"))
            prixProduits.add(produit.getDouble("prix"))
        }
    }
}
