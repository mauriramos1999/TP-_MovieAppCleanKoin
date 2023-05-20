package com.example.tp7_movieappcleankoin

import android.app.Application
import android.content.Context
import com.example.tp7_movieappcleankoin.dependencies.dataBaseModule
import com.example.tp7_movieappcleankoin.dependencies.modelModule
import com.example.tp7_movieappcleankoin.dependencies.retrofitModule
import com.example.tp7_movieappcleankoin.dependencies.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(retrofitModule,dataBaseModule,modelModule,viewModelModule))
        }
    }

}
