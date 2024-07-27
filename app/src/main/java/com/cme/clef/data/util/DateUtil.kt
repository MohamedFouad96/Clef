package com.cme.clef.data.util

import java.text.SimpleDateFormat


fun String.toEEEMMMddDateFormat(): String {

    if (isNotEmpty()) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = inputFormat.parse(this)

        val outputFormat = SimpleDateFormat("EEE MMM dd, yyyy")

        return date.let { outputFormat.format(it) }
    } else
        return "NA"

}


