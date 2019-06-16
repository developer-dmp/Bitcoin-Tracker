package com.dmp.blockchart.livedata

import androidx.lifecycle.MutableLiveData
import com.dmp.blockchart.network.ApiRequest
import com.dmp.blockchart.network.MarketPriceCallback
import com.dmp.blockchart.network.MarketPriceResponse
import retrofit2.Call

class MarketPriceRepository
{
    /**
     * Helper to make an API call and
     */
    fun fetchMarketPriceLiveData(): MutableLiveData<MarketPriceResponse>
    {
        val liveData = MutableLiveData<MarketPriceResponse>()

        ApiRequest.GetMarketPriceDataByJson(object : MarketPriceCallback()
        {
            override fun onError()
            {
                // possibly handle this better, but for now it works
                liveData.postValue(MarketPriceResponse(
                        "ERROR",
                        "Unable to load data, please try again.",
                        "",
                        "Empty Description",
                        ArrayList()
                ))
            }

            override fun onSuccess(marketPriceResponse: MarketPriceResponse)
            {
                liveData.postValue(marketPriceResponse)
            }

            override fun onFailure(call: Call<MarketPriceResponse>, t: Throwable)
            {
                onError()
            }
        })

        return liveData
    }
}