package com.up.estrelas.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.up.estrelas.entity.Estrela

@Database(entities = [Estrela::class], version = 1)
abstract class EstrelaDatabase: RoomDatabase() {

    abstract fun estrelaDao(): EstrelaDao

    companion object {

        private var INSTANCE: EstrelaDatabase? = null

        fun getDatabase(context: Context): EstrelaDatabase {
            return  INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EstrelaDatabase::class.java,
                    "star_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}