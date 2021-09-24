package com.example.pokemonapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ItemCardBinding
import com.example.pokemonapp.model.Card
import com.example.pokemonapp.util.loadWithGlide

class CardAdapter(
    private val cardList: MutableList<Card> = mutableListOf()
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = CardViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.loadCard(cardList[position])
    }

    override fun getItemCount() = cardList.size

    fun updateList(cards: List<Card>) {
        if (cards.lastOrNull() != cardList.lastOrNull()) {
            val positionStart = cardList.size
            cardList.addAll(cards)
            notifyItemRangeInserted(positionStart, cards.size)
        }
    }

    fun clear() {
        val listSize = cardList.size
        cardList.clear()
        notifyItemRangeRemoved(0, listSize)
    }

    class CardViewHolder(
        private val binding: ItemCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadCard(card: Card) = with(binding) {
//            ivCard.loadWithGlide(card.images.small)
            card.images?.small?.let { ivCard.loadWithGlide(it) }
            tvCardName.text = card.name
        }

        companion object {
            fun getInstance(parent: ViewGroup): CardViewHolder {
                val binding = ItemCardBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return CardViewHolder(binding)
            }
        }
    }
}