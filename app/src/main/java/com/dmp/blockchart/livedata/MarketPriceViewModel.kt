package com.dmp.blockchart.livedata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dmp.blockchart.network.MarketPriceResponse
import com.github.mikephil.charting.data.Entry

class MarketPriceViewModel(application: Application): AndroidViewModel(application)
{
    private val marketPriceRepository = MarketPriceRepository()

    val marketPriceLiveData: MutableLiveData<MarketPriceResponse> by lazy {
        marketPriceRepository.fetchMarketPriceLiveData()
    }

    val selectedEntryLiveData = MutableLiveData<Entry?>()


    fun setSelectedEntry(entry: Entry?)
    {
        selectedEntryLiveData.postValue(entry)
    }
}