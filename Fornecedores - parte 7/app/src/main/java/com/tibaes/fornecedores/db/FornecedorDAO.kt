package com.tibaes.fornecedores.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FornecedorDAO {

    @Insert
    fun insert(fornecedor: Fornecedor)

    @Update
    fun update(fornecedor: Fornecedor)

    @Delete
    fun delete(fornecedor: Fornecedor)

    @Query("SELECT * FROM fornecedor_tb ORDER BY nome_cl ASC")
    fun getAll():LiveData<List<Fornecedor>>

    @Query("SELECT * FROM fornecedor_tb WHERE id = :id_")
    fun getByID(id_: Int): LiveData<Fornecedor>

    @Query("DELETE FROM fornecedor_tb")
    fun deleteAll()
}