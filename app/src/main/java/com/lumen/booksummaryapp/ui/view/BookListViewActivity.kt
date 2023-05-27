package com.lumen.booksummaryapp.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lumen.booksummaryapp.model.Book
import com.lumen.booksummaryapp.ui.theme.BookSummaryAppTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lumen.booksummaryapp.viewmodel.BookListViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember

class BookListViewActivity : ComponentActivity() {
    private val viewModel : BookListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getBestSellers()
        setContent {
            BookSummaryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val books by viewModel.books.observeAsState(emptyList())
                    BookList(books = books)
                }
            }
        }
    }
}

@Composable
fun BookList(books: List<Book>) {
    LazyColumn {
        items(books) {book ->
            BookRow(book, book.imageUrl)
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                startIndent = 16.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    BookSummaryAppTheme {
        val books = listOf(
            Book("Sample Title 1", "Sample Author 1", "", ""),
            Book("Sample Title 2", "Sample Author 2", "", ""),
            Book("Sample Title 3", "Sample Author 3", "", ""),
            Book("Sample Title 4", "Sample Author 4", "", ""),
            Book("Sample Title 5", "Sample Author 5", "", "")
        )
        BookList(books)
    }
}