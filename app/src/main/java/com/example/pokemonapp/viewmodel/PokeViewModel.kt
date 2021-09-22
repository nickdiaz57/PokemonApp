package com.example.pokemonapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.model.Card
import com.example.pokemonapp.repo.PokeRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokeViewModel @Inject constructor(
    private val pokeRepo: PokeRepo
) : ViewModel() {

    private val _cardList = MutableLiveData<List<Card>>()
    val cardList: LiveData<List<Card>> get() = _cardList

    fun fetchCards() {
        viewModelScope.launch {
            pokeRepo.getCards(1, 1).collect { cards ->
                _cardList.postValue(cards)
            }
        }
    }
}