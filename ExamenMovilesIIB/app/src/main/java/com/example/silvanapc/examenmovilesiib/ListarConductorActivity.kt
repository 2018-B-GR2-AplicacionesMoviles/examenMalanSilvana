package com.example.silvanapc.examenmovilesiib

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_listar_conductor.*

class ListarConductorActivity : AppCompatActivity() {

    lateinit var adaptador: ConductorAdapter
    lateinit var conductores: ArrayList<Conductor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_conductor)

        conductores = BaseDatosConductor.getList()

        val layoutManager = LinearLayoutManager(this)
        adaptador = ConductorAdapter(conductores)
        recycler_view_conductor.layoutManager = layoutManager
        recycler_view_conductor.itemAnimator = DefaultItemAnimator()
        recycler_view_conductor.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_conductor)

    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var conductor = conductores[position]

        when (item.itemId) {

            R.id.item_menu_editar -> {
                val intent = Intent(this, CrearConductorActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("conductor", conductor)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Esta seguro de eliminar?")
                    .setPositiveButton("Si", { dialog, which ->
                        BaseDatosConductor.eliminarConductor(conductor.id)
                        finish()
                        startActivity(intent)
                    }
                    )
                    .setNegativeButton("No", null)
                val dialogo = builder.create()
                dialogo.show()
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
