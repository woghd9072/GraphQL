package com.jaehong.dto

import com.jaehong.entity.Author
import com.jaehong.entity.Book

data class BookResponse(
        val id: Long? = null,
        val name: String? = null,
        val pageCount: Int? = null,
        val author: Author? = null
) {
    fun from(entities: Collection<Book>): List<BookResponse> {
        val bookResponse = BookResponse()

        return entities.map { bookResponse.from(it) }
    }

    fun from(book: Book) = BookResponse(book.id, book.name, book.pageCount, book.author)
}