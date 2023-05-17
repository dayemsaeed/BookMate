package com.lumen.booksummaryapp.service

import com.lumen.booksummaryapp.utils.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("orderBy") orderBy: String = "relevance",
        @Query("key") key: String = "AIzaSyArzIVgXJt-Ot86aroQwfCRpbWeOYOgmc4"
    ) : Response<ApiResponse>

    @GET("books/v1/volumes")
    suspend fun getBestSellers(
        @Query("q") query: String = "subject:bestseller",
        @Query("orderBy") orderBy: String = "relevance",
        @Query("key") key: String = "AIzaSyArzIVgXJt-Ot86aroQwfCRpbWeOYOgmc4"
    ) : Response<ApiResponse>

}