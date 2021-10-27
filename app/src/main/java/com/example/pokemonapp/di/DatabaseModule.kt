package com.example.pokemonapp.di

import android.content.Context
import com.example.pokemonapp.repo.local.dao.PokeDao
import com.example.pokemonapp.repo.local.database.PokeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesPokeDatabase(
        @ApplicationContext context: Context
    ) : PokeDatabase = PokeDatabase.getInstance(context)

    @Provides
    fun providesPokeDao(database: PokeDatabase) : PokeDao = database.pokeDao()
}