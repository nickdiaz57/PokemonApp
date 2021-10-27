package com.example.pokemonapp.repo

import android.util.Log
import com.example.pokemonapp.model.Card
import com.example.pokemonapp.model.Queries
import com.example.pokemonapp.repo.local.dao.PokeDao
import com.example.pokemonapp.repo.remote.PokeService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokeRepo @Inject constructor(
    private val pokeService: PokeService,
    private val pokeDao: PokeDao
    ) {

    fun getCards(queries: Queries?) = flow {
        emit(pokeService.getCards(queries?.searchQuery, queries?.page, queries?.number).body()!!.data)
    }

    suspend fun favoriteCard(card: Card) {
        if (pokeDao.getCard(card.id) == null) {
            pokeDao.insert(card)
        } else {
            Log.e("PokeRepo", "already favorited")
        }
    }
}