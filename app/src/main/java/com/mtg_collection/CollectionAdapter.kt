package com.mtg_collection


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CollectionAdapter(private val cards: MutableList<Card>): RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var i: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val curCard = cards[position]
        holder.itemView.apply {
            val cardName = findViewById<TextView>(R.id.tvCardName)
            cardName.text = curCard.name
        }
    }

    fun deleteCard() { //TODO checked card
        if(cards.size > 0) {
            val card = cards[cards.size - 1]
            cards.remove(card)
        }
        notifyItemRemoved(cards.size)
    }

    fun addCard() {//TODO move to database
        val card = Card(123, "Hallal"+i)
        i++
        cards.add(card)
        notifyItemInserted(cards.size - 1)
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}