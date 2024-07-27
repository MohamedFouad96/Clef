package com.cme.clef.di


import com.cme.clef.data.cache.dao.AlbumsDao
import com.cme.clef.data.cache.dao.IAlbumsDao
import io.realm.Realm
import org.koin.dsl.module


val daoModule = module {
    single { createClefDao(get()) }
}

fun createClefDao(realm: Realm): IAlbumsDao {
    return AlbumsDao()
}

