package com.example.pokemonapp.repo.remote

import com.example.pokemonapp.model.Card
import com.example.pokemonapp.model.CardResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PokeService {

    @Headers("x-api-key: 75372df2-585d-49e5-84bf-a7e176ba9879")
    @GET("v2/cards")
    suspend fun getCards (
        @Query("q") q: String,
        @Query ("page") page: Int,
        @Query ("pageSize") pageSize: Int
    ) : Response<CardResponse>
}