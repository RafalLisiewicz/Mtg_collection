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
import java.util.Collections.addAll
import kotlin.concurrent.thread

class MtgApi {

    fun getCardByName(name: String): List<MtgCard>? {
        var cards: List<MtgCard>? = null
        val cardResponse: Response<List<MtgCard>> =
            MtgCardApiClient.getCardsByPartialName(name, 10, 0)
        cards = cardResponse.body()
        return cards
    }
}