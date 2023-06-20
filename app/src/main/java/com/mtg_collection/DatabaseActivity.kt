package com.mtg_collection

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class DatabaseActivity : AppCompatActivity() {

    private lateinit var databaseAdapter: DatabaseAdapter
    private val loader = MtgApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.database)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

    }

    @SuppressLint("MissingInflatedId")
    fun cardSearch(view: View){
        val etCardName = findViewById<EditText>(R.id.etCardName)
        MainScope().launch {
            val cards = loader.getCardByName(etCardName.text.toString())
            setContentView(R.layout.card_search)
            if (cards != null) {
                val cards = cards.toMutableList()
                cards.removeAll {it.imageUrl == null}
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