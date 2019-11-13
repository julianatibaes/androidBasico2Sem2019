package com.up.estrelas.repository

import android.arch.lifecycle.LiveData
import com.up.estrelas.db.EstrelaDao
import com.up.estrelas.entity.Estrela

class EstrelaRepository(private val estrelaDao: EstrelaDao) {

    val allEstrelas: LiveData<List<Estrela>> = estrelaDao.getAll()

    fun insert(estrela: Estrela){
        estrelaDao.insert(estrela)
    }

    fun update(estrela: Estrela){
        estrelaDao.update(estrela)
    }

    fun delete(estrela: Estrela){
        estrelaDao.delete(estrela)
    }
}