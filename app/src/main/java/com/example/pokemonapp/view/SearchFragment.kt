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
            val number = sliderCards.value.toInt()
            pokeViewModel.fetchCards(getQuery(), number)
            findNavController().navigateUp()
        }
    }
    //on submit get the query from the text box
    //query is sent to api formatted like q=name:char*
    //prepend 'q=name:' and append '*' to query from text box and submit

    //allow ability to switch between search by pokemon (cards) and search by card sets (sets) endpoints

    private fun getQuery() = with(binding) {
        return@with "name:" + nameEntry.text.toString() + "*"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}