package com.example.formaapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.formaapp.R
import com.example.formaapp.data.models.Formation

class FormationDetailsActivity : AppCompatActivity() {

    private var formation: Formation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formation_details)

        formation = intent.getSerializableExtra("formation") as? Formation

        // ✅ Récupère les vues
        findViewById<TextView>(R.id.titre).text = formation?.libelle ?: "-"
        findViewById<TextView>(R.id.description).text = formation?.description ?: "-"
        findViewById<TextView>(R.id.objectifs).text = formation?.objectifs ?: "-"
        findViewById<TextView>(R.id.public_vise).text = formation?.public_vise ?: "-"
        findViewById<TextView>(R.id.lieu).text = formation?.salle ?: "-"
        findViewById<TextView>(R.id.contenu).text = formation?.contenu ?: "-"
        findViewById<TextView>(R.id.cout).text = "Coût : ${formation?.cout ?: "-"} €"

        val dateDebut = formation?.date_debut ?: "?"
        val dateFin = formation?.date_fin ?: "?"
        findViewById<TextView>(R.id.dates).text = "Du $dateDebut au $dateFin"

        val intervenantsText = formation?.intervenants?.joinToString(", ") ?: "Non spécifiés"
        findViewById<TextView>(R.id.intervenants).text = "Intervenants : $intervenantsText"

        findViewById<Button>(R.id.buttonRetour).setOnClickListener {
            startActivity(Intent(this, ConsultationActivity::class.java))
            finish()
        }

        val btnDeconnexion = findViewById<Button?>(R.id.buttonDeconnexion)
        btnDeconnexion?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
