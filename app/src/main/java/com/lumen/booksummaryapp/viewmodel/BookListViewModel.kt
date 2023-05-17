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
    val books: LiveData<List<Book>> get() = _books
    private val TAG = "BookList"

    // Function to get the list of books
    fun getBooks(query: String) {
        viewModelScope.launch {
            try {
                val searchResults = bookRepository.searchBooks(query)
                _books.value = searchResults
            } catch (e: Exception) {
                Log.e(TAG, "Error getting books. Message: ${e.message}")
            }
        }
    }

    fun getBestSellers() {
        viewModelScope.launch {
            try {
                val searchResults = bookRepository.getBestSellers()
                _books.value = searchResults
            } catch (e: Exception) {
                Log.e(TAG, "Error getting best sellers. Message: ${e.message}")
            }
        }
    }

}