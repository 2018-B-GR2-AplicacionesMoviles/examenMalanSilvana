package com.example.silvanapc.examenmovilesiib

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView

class ConductorAdapter(private val conductorList: List<Conductor>) :  RecyclerView.Adapter<ConductorAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombre: TextView
        var apellido : TextView
        var fechaNacimiento: TextView
        var detalles: Button

        lateinit var conductor: Conductor

        init {
            nombre = view.findViewById(R.id.txtNombreConductor) as TextView
            apellido = view.findViewById(R.id.txtApellidoConductor) as TextView
            fechaNacimiento = view.findViewById(R.id.txtFechaNacimientoConductor) as TextView
            detalles = view.findViewById(R.id.button_guardar) as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, "Editar")
            menu?.add(Menu.NONE, R.id.item_menu_eliminar, Menu.NONE, "Eliminar")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_estudiante_layout, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val conductor = conductorList[position]
        holder.nombre.text = conductor.nombre
        holder.apellido.text = conductor.apellido
        holder.fechaNacimiento.text = conductor.fechaNacimiento
        holder.conductor = conductor
        holder.detalles.setOnClickListener{
                v: View ->
            val intent = Intent(v.context, ListarHijosActivity::class.java)
            intent.putExtra("detallesEstudiante", conductor)

            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return conductorList.size
    }


}