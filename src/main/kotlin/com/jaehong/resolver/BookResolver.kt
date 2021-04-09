package com.jaehong.resolver

import com.coxautodev.graphql.tools.GraphQLResolver
import com.jaehong.dto.BookResponse
import com.jaehong.entity.Author
import com.jaehong.entity.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BookResolver : GraphQLResolver<BookResponse> {

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    fun getAuthor(bookResponse: BookResponse): Author {
        return authorRepository.findById(bookResponse.author!!.id).orElse(null)
    }
}