package com.cme.clef

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import com.cme.clef.di.testModule
import com.cme.clef.data.di.daoModule
import com.cme.clef.data.di.remoteApiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        startKoin {
            androidContext(context)
            modules(listOf(testModule, daoModule, remoteApiModule))
        }
    }

}