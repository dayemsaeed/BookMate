package com.lumen.booksummaryapp.utils

import com.lumen.booksummaryapp.service.BookService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://www.googleapis.com/"

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val booksService: BookService by lazy {
        retrofit.create(BookService::class.java)
    }
}