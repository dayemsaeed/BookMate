package com.lumen.booksummaryapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lumen.booksummaryapp.model.Book
import com.lumen.booksummaryapp.repository.BookRepository
import java.lang.Exception

class BookPagingSource(
    private val bookRepository: BookRepository
) : PagingSource<Int, Book>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val page = params.key ?: 0
            val bestSellers = bookRepository.getBestSellers(page)
            LoadResult.Page(
                data = bestSellers,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (bestSellers.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}