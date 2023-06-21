package com.mtg_collection

import android.content.Context
import android.content.Intent
import android.hardware.SensorManager
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.seismic.ShakeDetector


class CollectionActivity : AppCompatActivity(), ShakeDetector.Listener {

    private lateinit var collectionAdapter: CollectionAdapter
    private lateinit var cards: MutableList<Card>
    private lateinit var curCard: Card

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection)

        //internet access
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        //database access
        val db = CardDB.getInstance(this)
        val dao = db.cardDao()
        cards = dao.loadAll()

        //making list
        collectionAdapter = CollectionAdapter(cards)
        val rvCardItems = findViewById<RecyclerView>(R.id.rvCardItems)
        rvCardItems.adapter = collectionAdapter
        rvCardItems.layoutManager = LinearLayoutManager(this)

        goCardView()

        //sensor
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sd = ShakeDetector(this)
        sd.start(sensorManager)
    }

    override fun hearShake() {
        collectionAdapter.shake()
    }


    fun deleteCard(view: View) {
        collectionAdapter.deleteCard(this, curCard)

        setContentView(R.layout.collection)
        collectionAdapter = CollectionAdapter(cards)
        val rvCardItems = findViewById<RecyclerView>(R.id.rvCardItems)
        rvCardItems.adapter = collectionAdapter
        rvCardItems.layoutManager = LinearLayoutManager(this)

        goCardView()
    }

    fun goBack(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goBackSearch(view: View) {
        setContentView(R.layout.collection)
        collectionAdapter = CollectionAdapter(cards)
        val rvCardItems = findViewById<RecyclerView>(R.id.rvCardItems)
        rvCardItems.adapter = collectionAdapter
        rvCardItems.layoutManager = LinearLayoutManager(this)

        goCardView()
    }

    fun goCardView() {
        collectionAdapter.setOnClickListener(object :
            CollectionAdapter.OnClickListener {
            override fun onClick(position: Int, card: Card) {
                setContentView(R.layout.card_page)
                curCard = card
                var button = findViewById<Button>(R.id.button3)
                button.visibility = View.INVISIBLE
                button = findViewById(R.id.buttonDelete)
                button.visibility = View.VISIBLE

                var cardField = findViewById<TextView>(R.id.textView4)
                cardField.text = card.name
                cardField = findViewById(R.id.tvSet)
                cardField.text = card.set
                cardField = findViewById(R.id.tvRarity)
                cardField.text = card.rarity
                cardField = findViewById(R.id.tvType)
                cardField.text = card.type
                cardField = findViewById(R.id.tvPower)
                if (card.power == "null/null") {
                    cardField.text = "None"
                } else {
                    cardField.text = card.power
                }
                cardField = findViewById(R.id.tvMana)
                cardField.text = card.cost
                cardField = findViewById(R.id.tvDescription)
                cardField.text = card.description
                val image = findViewById<ImageView>(R.id.imageView2)
                GlideApp
                    .with(image.context)
                    .load(card.imageUrl)
                    .centerCrop()
                    .into(image)
            }
        })
    }


}