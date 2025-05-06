package com.example.formaapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.formaapp.R
import com.example.formaapp.adapters.FormationAdapter
import com.example.formaapp.utils.XmlFormationHelper

class ConsultationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FormationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultation)

        recyclerView = findViewById(R.id.recyclerViewFormations)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val formations = XmlFormationHelper.lireFormationsDepuisRes(this)

        val textViewAucuneFormation = findViewById<TextView>(R.id.textViewAucuneFormation)

// Vérifie si la liste est vide
        if (formations.isEmpty()) {
            recyclerView.visibility = RecyclerView.GONE
            textViewAucuneFormation.visibility = TextView.VISIBLE
        } else {
            recyclerView.visibility = RecyclerView.VISIBLE
            textViewAucuneFormation.visibility = TextView.GONE
        }

        adapter = FormationAdapter(formations) { formation ->
            val intent = Intent(this, FormationDetailsActivity::class.java)
            intent.putExtra("formation", formation)
            startActivity(intent)
        }

        val btnDeconnexion = findViewById<Button>(R.id.buttonDeconnexion)

        btnDeconnexion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }


        recyclerView.adapter = adapter
    }
}
