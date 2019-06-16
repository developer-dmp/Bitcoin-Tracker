package com.dmp.blockchart.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dmp.blockchart.*
import com.dmp.blockchart.livedata.MarketPriceViewModel
import com.dmp.blockchart.network.DataPoint
import com.dmp.blockchart.network.MarketPriceResponse
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MarketPriceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar.apply {
            title = R.string.app_name.asString()
        })

        // fetch and observe our live data
        viewModel = ViewModelProviders.of(this).get(MarketPriceViewModel::class.java).apply {
            marketPriceLiveData.observe(this@MainActivity, Observer {
                onGotResponse(it)
            })

            selectedEntryLiveData.observe(this@MainActivity, Observer {
                updateSelectedEntry(it)
            })
        }

        // put the user in the loading state
        onStartLoadingState()
    }

    private fun onStartLoadingState() {
        title_textView.invisible()

        // progress bar in the middle of the graph
        chart.invisible()
        progressBar.show()
    }

    private fun onGotResponse(marketPriceResponse: MarketPriceResponse) {
        progressBar.hide()

        title_textView.apply {
            visible()
            text = marketPriceResponse.chartDescription
        }

        updateChartWithData(marketPriceResponse.dataPointList, marketPriceResponse.unit)
    }

    private fun updateChartWithData(dataPoints: ArrayList<DataPoint>, descriptionText: String) {
        val entries = dataPoints.map {
            Entry(it.xValue, it.yValue)
        }

        val lineDataSet = LineDataSet(entries, "Market Price ($descriptionText)").apply {
            color = android.R.color.black.asColor()
            lineWidth = 0f
            setCircleColor(R.color.colorPrimary.asColor())
            circleHoleRadius = .5f
        }

        chart.apply {
            visible()
            data = LineData(lineDataSet).apply {
                description = null
                xAxis.setDrawLabels(false) // hides the
            }
            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onNothingSelected() {
                    viewModel.setSelectedEntry(null)
                }

                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    viewModel.setSelectedEntry(e)
                }
            })
            invalidate()
        }
    }

    private fun updateSelectedEntry(entry: Entry?) {
        entry?.let {
            info_textView.apply {
                text = getFormattedTextFromEntry(entry)
                visible()
            }
        } ?: info_textView.invisible()
    }

    private fun getFormattedTextFromEntry(entry: Entry): String {
        val formattedDate =
            SimpleDateFormat("MMM. dd, yyyy", Locale.getDefault()).format(Date(entry.x.toLong() * 1000L))
        val formattedPrice = NumberFormat.getCurrencyInstance().format(entry.y)

        return "$formattedDate => $formattedPrice"
    }
}
