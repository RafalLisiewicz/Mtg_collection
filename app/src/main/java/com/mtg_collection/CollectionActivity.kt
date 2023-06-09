package com.mtg_collection

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.magicthegathering.kotlinsdk.model.card.MtgCard


class CollectionActivity : AppCompatActivity() {

    private lateinit var collectionAdapter: CollectionAdapter
    private lateinit var cards: MutableList<MtgCard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        cards = mutableListOf()

        collectionAdapter = CollectionAdapter(cards)
        val rvCardItems = findViewById<RecyclerView>(R.id.rvCardItems)
        rvCardItems.adapter = collectionAdapter
        rvCardItems.layoutManager = LinearLayoutManager(this)
    }


    fun deleteCard(view: View) {
        collectionAdapter.deleteCard()
    }

    fun addCard(view: View){
            collectionAdapter.addCard()
    }

    fun goBack(view: View){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}