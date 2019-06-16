package com.dmp.blockchart

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

class BlockChartApplication : Application()
{
    companion object
    {
        private lateinit var blockChartApplication: BlockChartApplication

        fun getContext(): BlockChartApplication
        {
            return blockChartApplication
        }
    }

    override fun onCreate()
    {
        super.onCreate()

        blockChartApplication = this
    }
}