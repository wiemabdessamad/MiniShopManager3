package com.example.minishopmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("MiniShop", "onCreate")

        setContentView(R.layout.activity_main)

        val buttonSuivant = findViewById<Button>(R.id.buttonSuivant)

        buttonSuivant.setOnClickListener {

            Toast.makeText(
                this,
                "Bonjour Ouma !",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MiniShop", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MiniShop", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MiniShop", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MiniShop", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MiniShop", "onDestroy")
    }
}