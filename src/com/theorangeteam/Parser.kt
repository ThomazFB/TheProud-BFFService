package com.theorangeteam.contract

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

open class Parser {

    inline fun <reified X> loadAdapter(): JsonAdapter<X>?
            = Moshi.Builder().build().adapter(X::class.java)

    inline fun <reified T> parseArrayFromJson(json: String): Array<T>? =
        loadAdapter<Array<T>>()?.fromJson(json)
}