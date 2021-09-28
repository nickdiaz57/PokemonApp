package com.example.pokemonapp.repo.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemonapp.model.Card
import com.example.pokemonapp.repo.local.dao.PokeDao

@Database(entities = [Card::class], version = 1, exportSchema = false)
@TypeConverters(PokeConverters::class)
abstract class PokeDatabase : RoomDatabase() {

    abstract fun pokeDao() : PokeDao

    companion object {
        @Volatile
        private var INSTANCE : PokeDatabase? = null

        fun getInstance(context: Context) : PokeDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokeDatabase::class.java,
                        "poke_card_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}