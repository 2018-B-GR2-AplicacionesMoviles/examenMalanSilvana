package com.example.silvanapc.examenmovilesiib

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        but_crearConductor.setOnClickListener {
            this.irACrearConductor()
        }

        but_listarConductor.setOnClickListener {
            this.irAListarConductor()
        }
    }
    fun irACrearConductor(){
        val intent = Intent(
            this,
            CrearConductorActivity::class.java
        )
        startActivity(intent)
    }
    fun irAListarConductor(){
        val intent = Intent(
            this,
            ListarConductorActivity::class.java
        )
        startActivity(intent)
    }
}
