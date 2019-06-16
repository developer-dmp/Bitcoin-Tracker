package com.dmp.blockchart.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

object ApiRequest
{

    private var apiService: ApiService
    private const val BASE_URL = "https://api.blockchain.info/charts/"

    init
    {
        apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun GetMarketPriceDataByJson(callback: MarketPriceCallback)
    {
        apiService.getMarketPriceData(HashMap<String, String>().apply {
            put("format", "json")
        }).enqueue(callback)
    }

    interface ApiService
    {

        @GET("market-price")
        fun getMarketPriceData(@QueryMap queryMap: HashMap<String, String>): Call<MarketPriceResponse>
    }
}