package com.tibaes.fornecedores.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Fornecedor::class], version = 1)
abstract class HelperDatabase: RoomDatabase() {

    abstract fun fornecedorDao(): FornecedorDAO

    companion object{
        private var INSTANCE: HelperDatabase? = null

        fun getDatabase(context: Context): HelperDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HelperDatabase::class.java,
                    "fornecedor_db"
                )   .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}