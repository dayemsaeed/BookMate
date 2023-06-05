package com.lumen.booksummaryapp.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.lumen.booksummaryapp.model.Book
import com.lumen.booksummaryapp.ui.theme.BookSummaryAppTheme
import com.lumen.booksummaryapp.viewmodel.BookListViewModel

class BookListViewActivity : ComponentActivity() {
    private val viewModel : BookListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getBestSellers()
        setContent {
            val books = viewModel.flow.collectAsLazyPagingItems()
            BookSummaryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BookList(books = books)
                }
            }
        }
    }
}

@Composable
fun BookList(books: LazyPagingItems<Book>, modifier: Modifier = Modifier) {
    when (books.loadState.refresh) {
        LoadState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
        is LoadState.Error -> {
            val message = books.loadState.refresh as LoadState.Error
            Text(
                text = message.error.localizedMessage ?: "Error loading books",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
        else -> {
            LazyColumn(
                state = rememberLazyListState(), modifier = Modifier
            ) {
                items(
                    count = books.itemCount,
                    key = books.itemKey<Book>(),
                    contentType = books.itemContentType<Book>()
                ) { index ->
                    val book = books[index]
                    book?.let {
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
        }
    }
}

/*
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
}*/
