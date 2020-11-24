package com.diary.network

import com.diary.database.Note
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://diary-api-akelen.herokuapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DiaryApiService {
    @GET("getlist")
    suspend fun getList() : List<Note>
}

object DiaryApi {
    val retrofitService : DiaryApiService by lazy { retrofit.create(DiaryApiService::class.java) }
}
