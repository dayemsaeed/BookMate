package com.lumen.booksummaryapp.ui.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lumen.booksummaryapp.model.Book

private const val placeholderUrl = "https://i.imgur.com/iOwNFqD.png"

@Composable
fun BookRow(book: Book, imageUrl : String = placeholderUrl) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Display book cover image
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Fit
        )
        // Display the book title and author
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        ) {
            Text(text = book.title, style = MaterialTheme.typography.h6)
            Text(text = book.author, style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookRowPreview() {
    val book = Book("Sample Title", "Sample Author", "", "")
    BookRow(book)
}

