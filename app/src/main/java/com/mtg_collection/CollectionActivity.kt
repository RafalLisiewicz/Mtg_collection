package com.mtg_collection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CollectionActivity : AppCompatActivity() {

    private lateinit var collectionAdapter: CollectionAdapter
    private lateinit var cards: MutableList<Card>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection)

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