package com.lumen.booksummaryapp.repository

import com.lumen.booksummaryapp.model.Book
import com.lumen.booksummaryapp.service.BookService
import com.lumen.booksummaryapp.utils.RetrofitClient
import java.io.IOException

class BookRepository {

    private val bookService: BookService = RetrofitClient.booksService;
    private val pageSize: Int = 10

    suspend fun searchBooks(query: String, startIndex: Int): List<Book> {
        val response = bookService.getBooks(query, startIndex, pageSize)
        if (response.isSuccessful) {
            val books = response.body()?.items?.map { item ->
                val volumeInfo = item.volumeInfo
                val title = volumeInfo.title
                val authors = volumeInfo.authors?.joinToString(", ")
                val description = volumeInfo.description ?: ""
                val imageLinks = volumeInfo.imageLinks?.thumbnail ?: ""

                Book(title, authors ?: "", description, imageLinks)
            }
            return books ?: emptyList()
        }
        else {
            throw IOException("Search request failed: ${response.code()}")
        }
    }

    suspend fun getBestSellers(startIndex: Int) : List<Book> {
        val response = bookService.getBestSellers(startIndex, pageSize)
        if (response.isSuccessful) {
            val books = response.body()?.items?.map { item ->
                val volumeInfo = item.volumeInfo
                val title = volumeInfo.title
                val authors = volumeInfo.authors?.joinToString(", ")
                val description = volumeInfo.description ?: ""
                val imageLinks = volumeInfo.imageLinks?.thumbnail ?: ""

                Book(title, authors ?: "", description, imageLinks)
            }
            return books ?: emptyList()
        }
        else {
            throw IOException("Search request failed: ${response.code()}")
        }
    }

}