package com.example.pokemonapp.repo.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pokemonapp.model.Card

@Dao
interface PokeDao {

    @Insert
    suspend fun insert(card: Card)

    @Delete
    suspend fun remove(card: Card)

    @Query ("SELECT * FROM poke_card_table WHERE id = :id")
    suspend fun getCard(id: String?) : Card

    @Query ("SELECT * FROM poke_card_table")
    suspend fun getAllCards() : List<Card>
}