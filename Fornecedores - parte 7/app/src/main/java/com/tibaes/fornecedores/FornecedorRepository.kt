package com.tibaes.fornecedores

import androidx.lifecycle.LiveData
import com.tibaes.fornecedores.db.Fornecedor
import com.tibaes.fornecedores.db.FornecedorDAO

class FornecedorRepository(private val fornecedorDAO: FornecedorDAO){

    fun insert(fornecedor: Fornecedor){
        fornecedorDAO.insert(fornecedor)
    }

    fun update(fornecedor: Fornecedor){
        fornecedorDAO.update(fornecedor)
    }

    fun delete(fornecedor: Fornecedor) =
        fornecedorDAO.delete(fornecedor)


    val fornecedores: LiveData<List<Fornecedor>> = fornecedorDAO.getAll()
}