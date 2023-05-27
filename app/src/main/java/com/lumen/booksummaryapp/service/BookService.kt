package com.lumen.booksummaryapp.service

import com.lumen.booksummaryapp.utils.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("startIndex") startIndex: Int,
        @Query("maxResults") maxResults: Int,
        @Query("orderBy") orderBy: String = "relevance",
        @Query("key") key: String = "AIzaSyArzIVgXJt-Ot86aroQwfCRpbWeOYOgmc4"
    ) : Response<ApiResponse>

    @GET("books/v1/volumes")
    suspend fun getBestSellers(
        @Query("startIndex") startIndex: Int,
        @Query("maxResults") maxResults: Int,
        @Query("q") query: String = "subject:bestseller",
        @Query("orderBy") orderBy: String = "relevance",
        @Query("key") key: String = "AIzaSyArzIVgXJt-Ot86aroQwfCRpbWeOYOgmc4"
    ) : Response<ApiResponse>

}