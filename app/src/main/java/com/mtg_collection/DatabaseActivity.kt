package com.mtg_collection

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DatabaseActivity : AppCompatActivity() {

    private lateinit var databaseAdapter: DatabaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.database)

    }

    @SuppressLint("MissingInflatedId")
    fun cardSearch(view: View){
        //list from API
        val etCardName = findViewById<EditText>(R.id.etCardName)

        var cards: MutableList<Card> = mutableListOf(Card(1,etCardName.text.toString()), Card(2,etCardName.text.toString()), Card(3, etCardName.text.toString()))

        setContentView(R.layout.card_search)

        databaseAdapter = DatabaseAdapter(cards)
        val rvCardItems = findViewById<RecyclerView>(R.id.rvCardItemsI)
        rvCardItems.adapter = databaseAdapter
        rvCardItems.layoutManager = LinearLayoutManager(this)
    }

    fun goBack(view: View){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goBackSearch(view: View){
        setContentView(R.layout.database)
    }


}