package com.example.pokemonapp.repo.remote

import com.example.pokemonapp.model.Card
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface PokeService {

    @Headers("x-api-key: 75372df2-585d-49e5-84bf-a7e176ba9879")
    @GET("v2/cards")
    suspend fun getCards () : Response<List<Card>> //TODO: add queries
}