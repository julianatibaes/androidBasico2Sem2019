package com.tibaes.fornecedores.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "fornecedor_tb")
class Fornecedor (
    @ColumnInfo(name = "nome_cl")
    var nome: String = "",

    @ColumnInfo(name = "email_cl")
    var email: String = "",

    @ColumnInfo(name = "tel_cl")
    var telefone: String = "",

    @ColumnInfo(name = "classificacao_cl")
    var classificacao: Float = 2F
): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

    


