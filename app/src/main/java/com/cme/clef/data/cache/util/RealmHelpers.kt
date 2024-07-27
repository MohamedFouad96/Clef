package com.cme.clef.data.cache.util

import io.realm.Realm


fun <T> getDefaultRealmInstance(realmBlock: (Realm) -> T): T {
   val realm = Realm.getDefaultInstance()
   val result =  realmBlock.invoke(realm)
    realm.close()

    return  result
}