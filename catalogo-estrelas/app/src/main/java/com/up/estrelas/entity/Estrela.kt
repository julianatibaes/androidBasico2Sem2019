package com.up.estrelas.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "star_table")
class Estrela (

    @NotNull
    @ColumnInfo(name = "description")
    var descricao: String = "",

    @ColumnInfo(name = "distance")
    var distnciaTerra: Float = 0.toFloat(),

    @ColumnInfo(name = "color")
    var cor: String = "",

    @ColumnInfo(name = "size")
    var tamanho: Float = 0F
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}