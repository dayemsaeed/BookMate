package com.lumen.booksummaryapp.utils

data class ApiResponse(
    val items: List<BookItem>?
)

data class BookItem(
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>?,
    val description: String?,
    val rating: Double,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val thumbnail: String
)
