package com.example.dogoftheday.network

import com.squareup.moshi.Json


//    this class only takes in data!
data class DogImage (@Json(name="message") val imgSrcUrl: String)

