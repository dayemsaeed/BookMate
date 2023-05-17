package com.lumen.booksummaryapp.repository

import com.lumen.booksummaryapp.model.Book
import com.lumen.booksummaryapp.service.BookService
import com.lumen.booksummaryapp.utils.RetrofitClient
import java.io.IOException

class BookRepository {

    private val bookService: BookService = RetrofitClient.booksService;

    suspend fun searchBooks(query: String): List<Book> {
        val response = bookService.getBooks(query)
        if (response.isSuccessful) {
            val books = response.body()?.items?.mapNotNull { item ->
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

    suspend fun getBestSellers() : List<Book> {
        val response = bookService.getBestSellers()
        if (response.isSuccessful) {
            val books = response.body()?.items?.mapNotNull { item ->
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