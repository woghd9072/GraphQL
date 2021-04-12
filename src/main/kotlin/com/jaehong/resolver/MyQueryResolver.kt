package com.jaehong.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.jaehong.entity.Book
import com.jaehong.entity.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MyQueryResolver : GraphQLQueryResolver {

    @Autowired
    private lateinit var bookRepository: BookRepository

    fun getBooks(): List<Book> {
        return bookRepository.findAll()
    }

    fun getBookById(id: Long): Book {
        return bookRepository.getOne(id)
    }
}