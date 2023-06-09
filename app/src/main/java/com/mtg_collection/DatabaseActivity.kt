package com.mtg_collection

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.RestrictTo.Scope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class DatabaseActivity : AppCompatActivity() {

    private lateinit var databaseAdapter: DatabaseAdapter
    val loader = MtgApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.database)

    }

    @SuppressLint("MissingInflatedId")
    fun cardSearch(view: View){
        thread {
            val etCardName = findViewById<EditText>(R.id.etCardName)
            val cards = loader.getCardByName(etCardName.text.toString())
            setContentView(R.layout.card_search)
            if (cards != null) {
                databaseAdapter = DatabaseAdapter(cards)
                val rvCardItems = findViewById<RecyclerView>(R.id.rvCardItemsI)
                rvCardItems.adapter = databaseAdapter
                rvCardItems.layoutManager = LinearLayoutManager(applicationContext)
            }
        }
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