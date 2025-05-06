package com.example.formaapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.formaapp.R
import com.example.formaapp.data.models.Stagiaire
import com.example.formaapp.utils.XmlStagiairesHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editEmail = findViewById<EditText>(R.id.editTextEmail)
        val editPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val stagiaires = XmlStagiairesHelper.lireStagiairesDepuisRes(this)

                val stagiaire = stagiaires.find { it.email == email && it.mdp == password }

                if (stagiaire != null) {
                    val intent = Intent(this, ConsultationActivity::class.java)
                    intent.putExtra("id_stagiaire", stagiaire.id_stagiaire)
                    intent.putExtra("nom", stagiaire.nom)
                    intent.putExtra("prenom", stagiaire.prenom)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Identifiants incorrects", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
