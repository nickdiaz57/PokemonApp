package com.example.pokemonapp.repo

import android.util.Log
import com.example.pokemonapp.model.Queries
import com.example.pokemonapp.repo.remote.PokeService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokeRepo @Inject constructor(private val pokeService: PokeService) {

    fun getCards(queries: Queries?) = flow {
        emit(pokeService.getCards(queries?.searchQuery, queries?.page, queries?.number).body()!!.data)
    }
}