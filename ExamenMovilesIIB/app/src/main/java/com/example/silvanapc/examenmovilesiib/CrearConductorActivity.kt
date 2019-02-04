package com.example.silvanapc.examenmovilesiib

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_crear_conductor.*

class CrearConductorActivity : AppCompatActivity() {

    var Conductor: Conductor? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_conductor)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textView_conductor.text = "Editar Conductor"
            Conductor = intent.getParcelableExtra("conductor")
            fillFields()
            tipo = true
        }

        but_iniciar.setOnClickListener { v: View? ->
            crearConductor()
        }
    }

    fun fillFields() {
        textView_nombre.setText(Conductor?.nombre)
        textView_apellido.setText(Conductor?.apellido)
        textView_fechaNacimiento.setText(Conductor?.fechaNacimiento)
        textView_numeroAutos.setText(Conductor?.numeroAutos.toString())
        if (Conductor?.licenciaValida == 1) {
            switch_licenciaValida.toggle()
        }
    }

    fun crearConductor(){
        var nombre = textView_nombre.text.toString()
        var apellido = textView_apellido.text.toString()
        var fechaNacimiento = textView_fechaNacimiento.text.toString()
        var numeroAutos = textView_numeroAutos.text.toString().toInt()
        var licenciaValida = if (switch_licenciaValida.isChecked) 1 else 0


        if (!tipo){

            var conductor = Conductor(0, nombre, apellido, fechaNacimiento, numeroAutos, licenciaValida,0,0)
            BaseDatosConductor.insertarConductor(conductor)

        }else{
            var conductor = Conductor(Conductor?.id!!, nombre, apellido, fechaNacimiento, numeroAutos, licenciaValida,0,0)
            BaseDatosConductor.actualizarConductor(conductor)
        }
        iraActividadEstudiante()

    }

    fun iraActividadEstudiante(){
        val intent = Intent(this, ListarConductorActivity::class.java)
        intent.putExtra("valorRol","VENDEDOR")
        startActivity(intent)
    }
}

