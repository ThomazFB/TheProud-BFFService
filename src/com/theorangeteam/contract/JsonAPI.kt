package com.theorangeteam.contract

interface JsonAPI {

    fun requestJsonListFromQuery(query: String): String
    fun requestSingleObject(objectID: Int): String
}