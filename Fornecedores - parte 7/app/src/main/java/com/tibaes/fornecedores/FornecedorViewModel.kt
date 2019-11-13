package com.tibaes.fornecedores

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tibaes.fornecedores.db.Fornecedor
import com.tibaes.fornecedores.db.FornecedorDAO
import com.tibaes.fornecedores.db.HelperDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FornecedorViewModel(application: Application):
    AndroidViewModel(application){

    private val repository: FornecedorRepository

    val fornecedores: LiveData<List<Fornecedor>>


    private val parentJob = Job()
    private val corountineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(corountineContext)

    init {
        val fornecedorDAO = HelperDatabase.getDatabase(application).fornecedorDao()
        repository = FornecedorRepository(fornecedorDAO)

        fornecedores = repository.fornecedores
    }

    fun insert(fornecedor: Fornecedor) = scope.launch(Dispatchers.IO){
        repository.insert(fornecedor)
    }

    fun update(fornecedor: Fornecedor) = scope.launch(Dispatchers.IO){
        repository.update(fornecedor)
    }

    fun delete(fornecedor: Fornecedor) = scope.launch(Dispatchers.IO){
        repository.delete(fornecedor)
    }
}