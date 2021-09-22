package com.example.pokemonapp.repo

import com.example.pokemonapp.repo.remote.PokeService
import javax.inject.Inject

class PokeRepo @Inject constructor(private val pokeService: PokeService) {

}