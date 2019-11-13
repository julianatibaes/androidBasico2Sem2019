package com.up.estrelas.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.up.estrelas.entity.Estrela

@Dao
interface EstrelaDao {

    @Insert
    fun insert(estrela: Estrela)

    @Update
    fun update(estrea: Estrela)

    @Delete
    fun delete(estrela: Estrela)

    @Query("SELECT * FROM star_table ORDER BY description ASC")
    fun getAll(): LiveData<List<Estrela>>

    @Query("SELECT * FROM star_table WHERE id = :id_")
    fun getEstrela(id_: Long): LiveData<Estrela>

    @Query("DELETE FROM star_table")
    fun deleteAll()
}