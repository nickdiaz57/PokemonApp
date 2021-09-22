package com.example.pokemonapp.repo

import com.example.pokemonapp.repo.remote.PokeService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokeRepo @Inject constructor(private val pokeService: PokeService) {

    fun getCards(page: Int, pageSize: Int) = flow {
        emit(pokeService.getCards(page, pageSize).body()!!)
    }
}