package com.example.silvanapc.examenmovilesiib

import android.os.Parcel
import android.os.Parcelable

class Auto(var id: Int, var chasis: Int, var nombreMarca: String, var colorUno: String, var colorDos: String,
           var nombreModelo: String, var anio: Int, var idConductor:Int, var createdAt: Long,
           var updatedAt: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readLong()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(chasis)
        parcel.writeString(nombreMarca)
        parcel.writeString(colorUno)
        parcel.writeString(colorDos)
        parcel.writeString(nombreModelo)
        parcel.writeInt(anio)
        parcel.writeInt(idConductor)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Auto> {
        override fun createFromParcel(parcel: Parcel): Auto {
            return Auto(parcel)
        }

        override fun newArray(size: Int): Array<Auto?> {
            return arrayOfNulls(size)
        }
    }
}