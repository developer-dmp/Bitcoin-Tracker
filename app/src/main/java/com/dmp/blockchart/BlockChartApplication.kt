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

        fun <T: ViewModel> getViewModel(modelClass: Class<T>): T
        {
            return getContext().viewModelProvider.get(modelClass)
        }
    }

    private val viewModelProvider = ViewModelProvider(ViewModelStore(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(this))

    override fun onCreate()
    {
        super.onCreate()

        blockChartApplication = this
    }
}