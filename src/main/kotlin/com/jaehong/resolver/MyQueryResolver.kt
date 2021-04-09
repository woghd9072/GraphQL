package com.jaehong.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.jaehong.dto.BookResponse
import com.jaehong.entity.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MyQueryResolver : GraphQLQueryResolver {

    @Autowired
    private lateinit var bookRepository: BookRepository

    fun getBooks(): List<BookResponse> {
        val all = bookRepository.findAll()
        return BookResponse().from(all)
    }

    fun getBookById(id: Long): BookResponse {
        return BookResponse().from(bookRepository.getOne(id))
    }
}