package com.tibaes.fornecedores

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tibaes.fornecedores.db.Fornecedor
import com.tibaes.fornecedores.db.FornecedorDAO
import com.tibaes.fornecedores.db.HelperDatabase

class FornecedorViewModel(application: Application):
    AndroidViewModel(application){

    private val repository: FornecedorRepository

    val fornecedores: LiveData<List<Fornecedor>>

    init {
        val fornecedorDAO = HelperDatabase.getDatabase(application).fornecedorDao()
        repository = FornecedorRepository(fornecedorDAO)

        fornecedores = repository.fornecedores
    }

    fun insert(fornecedor: Fornecedor){
        repository.insert(fornecedor)
    }

    fun update(fornecedor: Fornecedor){
        repository.update(fornecedor)
    }

    fun delete(fornecedor: Fornecedor){
        repository.delete(fornecedor)
    }
}