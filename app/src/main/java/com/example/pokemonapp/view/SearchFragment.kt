package com.example.pokemonapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokemonapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
//settings fragment equivalent
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSearchBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root
    //on submit get the query from the text box
    //query is sent to api formatted like q=name:char*
    //prepend 'q=name:' and append '*' to query from text box and submit

    //allow ability to switch between search by pokemon (cards) and search by card sets (sets) endpoints
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}