package com.sunilsharma.forecastmvvm.data.db.entity


import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("language")
    val language: String, // en
    @SerializedName("query")
    val query: String, // London, United Kingdom
    @SerializedName("type")
    val type: String, // City
    @SerializedName("unit")
    val unit: String // m
)