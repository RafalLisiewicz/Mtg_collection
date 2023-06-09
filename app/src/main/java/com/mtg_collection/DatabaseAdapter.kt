package com.mtg_collection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.magicthegathering.kotlinsdk.model.card.MtgCard

class DatabaseAdapter (private val cards: List<MtgCard>): RecyclerView.Adapter<DatabaseAdapter.DatabaseViewHolder>() {

    class DatabaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatabaseViewHolder {
        return DatabaseViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_card1,
                parent,
                false
            )
        )
    }

    /*fun addCard(card: Card) {
        cards.add(card)
        notifyItemInserted(cards.size - 1)
    }*/

    override fun onBindViewHolder(holder: DatabaseViewHolder, position: Int) {
        val curCard = cards[position]
        holder.itemView.apply {
            val cardName = findViewById<TextView>(R.id.tvCardNameI)
            cardName.text = curCard.name
            val image = findViewById<ImageView>(R.id.imageViewI)
            image.setImageResource(R.drawable.card_example)
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}