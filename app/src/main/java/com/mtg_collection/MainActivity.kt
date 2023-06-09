package com.mtg_collection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var databaseAdapter: DatabaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun btnCollection(view: View){
        val intent = Intent(applicationContext, CollectionActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun btnBackMain(view: View){
        setContentView(R.layout.activity_main)
    }
    fun btnDatabase(view: View){
        val intent = Intent(applicationContext, DatabaseActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun btnAdd(view: View){
        val cardName = "ALALAL"
        val card = Card(1,cardName)
       // databaseAdapter.addCard(card)
    }

}