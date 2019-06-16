package com.dmp.blockchart.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class MarketPriceCallback: Callback<MarketPriceResponse>
{
    abstract fun onError()
    abstract fun onSuccess(marketPriceResponse: MarketPriceResponse)

    override fun onResponse(call: Call<MarketPriceResponse>,
                            response: Response<MarketPriceResponse>)
    {
        if (response.isSuccessful)
        {
            response.body()?.let {
                onSuccess(it)
            } ?: onError()

            return
        }

        onError()
    }
}