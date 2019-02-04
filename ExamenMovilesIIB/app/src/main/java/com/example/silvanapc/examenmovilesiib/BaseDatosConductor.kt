package com.example.silvanapc.examenmovilesiib

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.*

class BaseDatosConductor {

    companion object {

        fun insertarConductor(conductor: Conductor){
            "http://192.168.1.9:1337/Conductor".httpPost(listOf("nombre" to conductor.nombre,
                "apellido" to conductor.apellido, "fechaNacimiento" to conductor.fechaNacimiento,
                "numeroAutos" to conductor.numeroAutos, "licenciaValida" to conductor.licenciaValida))
                .responseString { request, _, result ->
                    Log.d("http-ejemplo", request.toString())
                }
        }

        fun eliminarConductor(id: Int) {
            "http://192.168.1.9:1337/Conductor/$id".httpDelete()
                .responseString { request, response, result ->
                    Log.d("http-ejemplo", request.toString())
                }
        }

        fun actualizarConductor(conductor: Conductor) {
            "http://192.168.1.9:1337/Conductor/${conductor.id}".httpPut(listOf("nombre" to conductor.nombre,
                "apellidos" to conductor.apellido, "fechaNacimiento" to conductor.fechaNacimiento,
                "numeroAutos" to conductor.numeroAutos, "licenciaValida" to conductor.licenciaValida))
                .responseString { request, _, result ->
                    Log.d("http-ejemplo", request.toString())
                }
        }

        fun getList(): ArrayList<Conductor> {
            val conductores: ArrayList<Conductor> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.1.9:1337/ Conductor".httpGet().responseString()
            val jsonStringEstudiante = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringEstudiante)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombres"] as String
                val apellido = it["apellidos"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val numeroAutos = it["semestreActual"] as Int
                val licenciaValida = it["graduado"] as Int
                val estudiante = Conductor(id, nombre, apellido, fechaNacimiento, numeroAutos, licenciaValida, 0, 0)
                conductores.add(estudiante)
            }
            return conductores
        }

        fun buscarConductor(nombre:String): ArrayList<Conductor> {
            val conductores: ArrayList<Conductor> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.1.9:1337/Conductor?nombres=${nombre}".httpGet().responseString()
            val jsonStringEstudiante = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringEstudiante)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombres"] as String
                val apellido = it["apellidos"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val numeroAutos = it["numeroAutos"] as Int
                val licenciaValida = it["licenciaValida"] as Int
                val conductor = Conductor(id, nombre, apellido, fechaNacimiento, numeroAutos, licenciaValida, 0, 0)
                conductores.add(conductor)
            }
            return conductores
        }

    }
}