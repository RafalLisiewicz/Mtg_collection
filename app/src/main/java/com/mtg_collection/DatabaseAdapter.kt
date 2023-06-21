package com.mtg_collection

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import kotlinx.coroutines.withContext


class DatabaseAdapter (private val cards: List<MtgCard>): RecyclerView.Adapter<DatabaseAdapter.DatabaseViewHolder>() {

    class DatabaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatabaseViewHolder {
        return DatabaseViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_card1,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: DatabaseViewHolder, position: Int) {
        val curCard = cards[position]
        holder.itemView.apply {
            val cardName = findViewById<TextView>(R.id.tvCardNameI)
            cardName.text = curCard.name
            val image = findViewById<ImageView>(R.id.imageViewI)
            GlideApp
                .with(image.context)
                .load(curCard.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .centerCrop()
                .into(image);
        }
        holder.itemView.setOnClickListener {
            if(onClickListener != null){
                onClickListener!!.onClick(position, curCard)
            }
        }
    }
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: MtgCard)
    }

    fun addDB(context: Context, card: MtgCard){
        val db = CardDB.getInstance(context)
        val dao = db.cardDao()
        dao.insert(Card(card.multiverseid, card.name, card.imageUrl, card.setName, card.rarity, card.type, card.power+"/"+card.toughness, card.manaCost, card.text))
    }

    override fun getItemCount(): Int {
        return cards.size
    }

}