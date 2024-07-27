package com.cme.clef.util

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

internal fun MockWebServer.enqueueResponse(fileName: String, code: Int, token: String = "") {
    javaClass.classLoader?.let {
        val inputStream = it.getResource("api-responses-for-testing/$fileName").readText()


        enqueue(
            if (token.isEmpty()) {
                MockResponse()
                    .setResponseCode(code)
                    .setBody(inputStream)
            } else if (token == "xxxx.test") {
                MockResponse()
                    .setResponseCode(code)
                    .setBody(inputStream)
            } else {
                MockResponse()
                    .setResponseCode(code)
            }

        )
    }

}


object Files {

    const val ALBUMS_FILE = "albums/albums_response.json"
    const val EMPTY_FILE = "empty.json"
    const val NULL_FILE = "null.json"


}



