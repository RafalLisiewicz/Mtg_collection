package com.mtg_collection

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.concurrent.thread

class MtgApi{

    fun getCardByName(name: String): MtgCard? {
        var card: MtgCard? = null
        val cardResponse: Response<List<MtgCard>> = MtgCardApiClient.getCardsByPartialName(name, 1, 0)
        card = cardResponse.body()?.get(0)
        return card
    }
}