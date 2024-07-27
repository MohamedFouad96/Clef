package com.cme.clef.data.di

import com.cme.clef.data.remote.api.ClefApi
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteApiModule = module {
    single { createClefService(get()) }
}

fun createClefService(retrofit: Retrofit) : ClefApi {
    return  retrofit.create(ClefApi::class.java)
}