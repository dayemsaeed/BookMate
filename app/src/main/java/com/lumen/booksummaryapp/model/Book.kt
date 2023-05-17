package com.lumen.booksummaryapp.model

data class Book(
    val title: String,
    val author: String,
    val description: String = "No Description",
    val imageUrl: String
)
