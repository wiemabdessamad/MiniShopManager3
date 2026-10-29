
package com.example.minishopmanager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val cbRemember = findViewById<CheckBox>(R.id.cbRemember)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // Récupérer les informations enregistrées
        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Préremplir le nom si l'utilisateur l'avait enregistré
        val savedUsername = prefs.getString("USERNAME", "")
        etUsername.setText(savedUsername)

        // Restaurer l'état de la case
        cbRemember.isChecked = prefs.getBoolean("REMEMBER", false)

        btnLogin.setOnClickListener {

            val username = etUsername.text.toString().trim()

            if (username.isEmpty()) {
                Toast.makeText(
                    this,
                    "Veuillez saisir votre nom",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val editor = prefs.edit()

                if (cbRemember.isChecked) {
                    editor.putString("USERNAME", username)
                    editor.putBoolean("REMEMBER", true)
                } else {
                    editor.remove("USERNAME")
                    editor.putBoolean("REMEMBER", false)
                }

                editor.apply()
                Toast.makeText(
                    this,
                    "Bienvenue $username !",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this, ProduitsActivity::class.java)
                startActivity(intent)

            }
        }
    }
}
