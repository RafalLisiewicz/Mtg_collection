package com.mtg_collection

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import kotlinx.coroutines.*

class DatabaseActivity : AppCompatActivity() {

    private lateinit var databaseAdapter: DatabaseAdapter
    private val loader = MtgApi()
    private lateinit var card: MtgCard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.database)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

    }

    @SuppressLint("MissingInflatedId")
    fun cardSearch(view: View) {
        val etCardName = findViewById<EditText>(R.id.etCardName)
        MainScope().launch {
            val cards = loader.getCardByName(etCardName.text.toString())
            setContentView(R.layout.card_search)
            if (cards != null) {
                val cards = cards.toMutableList()
                cards.removeAll { it.imageUrl == null }
                databaseAdapter = DatabaseAdapter(cards)
                val rvCardItems = findViewById<RecyclerView>(R.id.rvCardItemsI)
                rvCardItems.adapter = databaseAdapter
                rvCardItems.layoutManager = LinearLayoutManager(applicationContext)

                goCardView(view)
            }
        }
    }

    fun goBack(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goBackSearch(view: View) {
        setContentView(R.layout.database)
    }

    fun goCardView(view: View) {
        databaseAdapter.setOnClickListener(object :
            DatabaseAdapter.OnClickListener {
            override fun onClick(position: Int, model: MtgCard) {
                card = model
                setContentView(R.layout.card_page)
                var button = findViewById<Button>(R.id.buttonDelete)
                button.visibility = View.INVISIBLE
                button = findViewById<Button>(R.id.button3)
                button.visibility = View.VISIBLE
                var cardField = findViewById<TextView>(R.id.textView4)
                cardField.text = model.name
                cardField = findViewById(R.id.tvSet)
                cardField.text = model.setName
                cardField = findViewById(R.id.tvRarity)
                cardField.text = model.rarity
                cardField = findViewById(R.id.tvType)
                cardField.text = model.type
                cardField = findViewById(R.id.tvPower)
                if (model.power == null) {
                    cardField.text = "None"
                } else {
                    cardField.text = model.power + "/" + model.toughness
                }
                cardField = findViewById(R.id.tvMana)
                cardField.text = model.manaCost
                cardField = findViewById(R.id.tvDescription)
                cardField.text = model.text
                val image = findViewById<ImageView>(R.id.imageView2)
                GlideApp
                    .with(image.context)
                    .load(model.imageUrl)
                    .centerCrop()
                    .into(image)
            }
        })
    }

    fun addToDB(view: View) {
        databaseAdapter.addDB(this, card)
    }


}