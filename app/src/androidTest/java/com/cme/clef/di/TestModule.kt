package com.cme.clef.di

import android.content.Context
import com.cme.clef.data.remote.util.calladapter.FlowCallAdapterFactory
import com.cme.clef.data.util.DataSourceConstants.DATABASE_NAME
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val testModule = module {
    single { createDatabase(androidContext()) }
    single { createMockWebServer() }
    single { createOkHttpClient() }
    single { createFakeRetrofit(get(),get()) }
}

fun createDatabase(context: Context): Realm{
    Realm.init(context)

    val config = RealmConfiguration.Builder()
        .name("testing-$DATABASE_NAME")
        .inMemory()
        .schemaVersion(1)
        .build()

    Realm.setDefaultConfiguration(config)

    return Realm.getDefaultInstance()
}

fun createMockWebServer(): MockWebServer {
    return MockWebServer()
}


fun createFakeRetrofit(mockWebServer: MockWebServer,okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().client(okHttpClient)
        .baseUrl(mockWebServer.url("").toUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .build()

}


fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .callTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}