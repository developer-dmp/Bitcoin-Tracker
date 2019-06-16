package com.dmp.blockchart.network

import com.google.gson.annotations.SerializedName

data class MarketPriceResponse(
    @SerializedName("status") val status: String,
    @SerializedName("name") val chartTitle: String,
    @SerializedName("unit") val unit: String,
    @SerializedName("description") val chartDescription: String,
    @SerializedName("values") val dataPointList: ArrayList<DataPoint>
)

/**
 * TODO: Would need to update this class to handle scientific notation from the endpoint
 */
data class DataPoint(
    @SerializedName("x") val xValue: Float,
    @SerializedName("y") val yValue: Float
)