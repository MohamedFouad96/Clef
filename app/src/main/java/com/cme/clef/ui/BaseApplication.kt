package com.cme.clef.ui

import android.app.Application
import com.cme.clef.di.daoModule
import com.cme.clef.di.productionModule
import com.cme.clef.di.remoteApiModule
import com.cme.clef.di.repoModule
import com.cme.clef.di.viewModelModules
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        initKoin()
    }


    private fun initKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(productionModule, remoteApiModule, daoModule, repoModule,viewModelModules))

        }
    }

}