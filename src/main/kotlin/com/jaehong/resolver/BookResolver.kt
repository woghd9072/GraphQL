package com.jaehong.resolver

import com.coxautodev.graphql.tools.GraphQLResolver
import com.jaehong.entity.Author
import com.jaehong.entity.AuthorRepository
import com.jaehong.entity.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BookResolver : GraphQLResolver<Book> {

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    fun getAuthor(book: Book): Author {
        return authorRepository.findById(book.author.id).orElse(null)
    }
}