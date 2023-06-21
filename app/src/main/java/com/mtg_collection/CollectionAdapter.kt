package com.mtg_collection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CollectionAdapter(private val cards: MutableList<Card>) :
    RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var onClickListener: OnClickListener? = null

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
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, curCard)
            }
        }
    }

    fun shake() {
        cards.shuffle()
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, card: Card)
    }

    fun deleteCard(context: Context, card: Card) {
        val db = CardDB.getInstance(context)
        val dao = db.cardDao()
        dao.delete(card)
        cards.remove(card)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}