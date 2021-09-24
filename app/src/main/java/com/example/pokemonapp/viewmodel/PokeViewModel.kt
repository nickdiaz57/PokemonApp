package com.example.pokemonapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.model.Card
import com.example.pokemonapp.repo.PokeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PokeViewModel @Inject constructor(
    private val pokeRepo: PokeRepo
) : ViewModel() {

    private val _cardList = MutableLiveData<List<Card>>()
    val cardList: LiveData<List<Card>> get() = _cardList

    fun fetchCards() {
        viewModelScope.launch {
            pokeRepo.getCards(1, 10).collect { cards ->
                _cardList.postValue(cards)
            }
        }
    }

//    fun fetchRandomCard() {
//        val counter = Random.nextInt(1, 13935)
//        viewModelScope.launch {
//            pokeRepo.getCards(counter, 1).collect { card ->
//                _cardList.postValue(card)
//            }
//        }
//    }
}