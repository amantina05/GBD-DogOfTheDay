package com.example.dogoftheday.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://dog.ceo/api/breeds/image/"


//prints to terminal
private val networkLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

//breaks down response we see into json
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//whenever we get an okay response print that to the log cat so we can see, convert it to kotlin
private val retrofit = Retrofit.Builder()
    .client(OkHttpClient.Builder().addInterceptor(networkLoggingInterceptor).build())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//interface that we want data to adhere to
interface  DogImageApiService {
    @GET("random")
    suspend fun getRandomDogImage(): DogImage
}

//telling it to create a service/ object where we can use this func inside of it
object DogImageApi {
    val retrofitService: DogImageApiService by lazy {retrofit.create(DogImageApiService::class.java)}
}