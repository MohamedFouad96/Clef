package com.cme.clef.di

import android.content.Context
import com.cme.clef.data.remote.util.calladapter.FlowCallAdapterFactory
import com.cme.clef.data.remote.util.config.ConnectivityAwareUrlClient
import com.cme.clef.data.util.DataSourceConstants.DATABASE_NAME
import com.cme.clef.data.util.DataSourceConstants.NETWORK_BASE_URL
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val productionModule = module {
    single { createDatabase(androidContext()) }
    single { createOkHttpClient(androidContext()) }
    single { createRetrofit(get()) }
}

fun createDatabase(context: Context): Realm {

    Realm.init(context)

    val config = RealmConfiguration.Builder()
        .name(DATABASE_NAME)
        .allowWritesOnUiThread(true)
        .allowQueriesOnUiThread(true)
        .schemaVersion(1)
        .build()

    Realm.setDefaultConfiguration(config)

    return Realm.getDefaultInstance()
}


fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().client(okHttpClient)
        .baseUrl(NETWORK_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .build()
}


fun createOkHttpClient( context: Context): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .callTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(ConnectivityAwareUrlClient(context))
        .addInterceptor(httpLoggingInterceptor)
        .build()
}