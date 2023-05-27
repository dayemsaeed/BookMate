package com.lumen.booksummaryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumen.booksummaryapp.model.Book
import com.lumen.booksummaryapp.repository.BookRepository
import kotlinx.coroutines.launch

class BookListViewModel : ViewModel() {

    private val bookRepository : BookRepository = BookRepository()
    private val _books: MutableLiveData<List<Book>> = MutableLiveData()
    private var startIndex = 0
    val books: LiveData<List<Book>> get() = _books
    private val TAG = "BookList"

    // Function to get the list of books
    fun getBooks(query: String) {
        viewModelScope.launch {
            try {
                val searchResults = bookRepository.searchBooks(query, startIndex)
                _books.value = searchResults
            } catch (e: Exception) {
                Log.e(TAG, "Error getting books. Message: ${e.message}")
            }
        }
    }

    fun getBestSellers() {
        viewModelScope.launch {
            try {
                val searchResults = bookRepository.getBestSellers(startIndex)
                _books.value = searchResults
            } catch (e: Exception) {
                Log.e(TAG, "Error getting best sellers. Message: ${e.message}")
            }
        }
    }

    suspend fun loadMoreBestSellers() {
        try {
            val bestSellers = bookRepository.getBestSellers(startIndex)
            val currentBooks = _books.value.orEmpty().toMutableList()
            currentBooks.addAll(bestSellers)
            _books.value = currentBooks
            startIndex += bestSellers.size
        } catch (e: Exception) {
            // Handle error
        }
    }

}