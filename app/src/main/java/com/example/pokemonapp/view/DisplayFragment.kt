package com.example.pokemonapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.adapter.CardAdapter
import com.example.pokemonapp.databinding.FragmentDisplayBinding
import com.example.pokemonapp.model.Card
import com.example.pokemonapp.util.PageAction
import com.example.pokemonapp.viewmodel.PokeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayFragment : Fragment() {
//home fragment
    private var _binding: FragmentDisplayBinding? = null
    private val binding get() = _binding!!
    private val pokeViewModel by activityViewModels<PokeViewModel>()
    private val cardAdapter by lazy { CardAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDisplayBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupObserver()
    }

    private fun initView() = with(binding) {
        rvList.adapter = cardAdapter
        rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && dy > 0) {
                    pokeViewModel.fetchCards(PageAction.NEXT)
                }
            }
        })
    }

    private fun setupObserver() = with(pokeViewModel) {
        cardList.observe(viewLifecycleOwner) {
            loadCards(it)
        }
    }

    private fun loadCards(cards: List<Card>) = with(binding.rvList) {
        if (pokeViewModel.currentPageAction == PageAction.FIRST) cardAdapter.clear()
        cardAdapter.updateList(cards)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}