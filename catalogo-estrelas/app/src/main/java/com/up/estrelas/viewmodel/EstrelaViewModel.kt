package com.up.estrelas.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.up.estrelas.db.EstrelaDatabase
import com.up.estrelas.entity.Estrela
import com.up.estrelas.repository.EstrelaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EstrelaViewModel(application: Application):
    AndroidViewModel(application) {

    private val parentJob = Job()
    private val corountineContext: CoroutineContext
    get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(corountineContext)

    private val repository: EstrelaRepository
    val allStar: LiveData<List<Estrela>>

    init {
        val estrelaDao = EstrelaDatabase.getDatabase(application).estrelaDao()
        repository = EstrelaRepository(estrelaDao)
        allStar = repository.allEstrelas
    }

    fun insert(estrela: Estrela) = scope.launch(Dispatchers.IO){
        repository.insert(estrela)
    }

    fun update(estrela: Estrela) = scope.launch(Dispatchers.IO){
        repository.update(estrela)
    }

    fun delete(estrela: Estrela) = scope.launch(Dispatchers.IO){
        repository.delete(estrela)
    }
}