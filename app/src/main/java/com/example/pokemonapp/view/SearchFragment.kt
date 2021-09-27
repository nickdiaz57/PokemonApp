package com.example.pokemonapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.databinding.FragmentSearchBinding
import com.example.pokemonapp.model.Queries
import com.example.pokemonapp.viewmodel.PokeViewModel

class SearchFragment : Fragment() {
//settings fragment equivalent
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val pokeViewModel by activityViewModels<PokeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSearchBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        btnSubmit.setOnClickListener{
            pokeViewModel.fetchCards(getQueries())
            findNavController().navigateUp()
        }
    }

    //allow ability to switch between search by pokemon (cards) and search by card sets (sets) endpoints

    private fun getNameInput() = with(binding) {
        return@with "name:${nameEntry.text.toString()}*"
    }

    private fun getNumber() = with(binding) {
        return@with sliderCards.value.toInt()
    }

    private fun getQueries() = Queries(
        searchQuery = getNameInput(),
        page = pokeViewModel.queries?.page,
        number = getNumber()
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}