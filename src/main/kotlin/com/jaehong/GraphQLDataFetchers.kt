package com.jaehong

import com.google.common.collect.ImmutableMap
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component


@Component
class GraphQLDataFetchers() {
    val bookByIdDataFetcher: DataFetcher<*>
        get() = DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val bookId: String = dataFetchingEnvironment.getArgument("id")
            books
                .stream()
                .filter { book: Map<String, String>? ->
                    (book!!["id"] == bookId)
                }
                .findFirst()
                .orElse(null)
        }

    val authorDataFetcher: DataFetcher<*>
        get() {
            return DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
                val book: Map<String, String> =
                    dataFetchingEnvironment.getSource()
                val authorId: String? = book["authorId"]
                authors
                    .stream()
                    .filter { author: Map<String, String>? ->
                        (author!!["id"] == authorId)
                    }
                    .findFirst()
                    .orElse(null)
            }
        }

    val allDataFetcher: DataFetcher<*>
        get() {
            return DataFetcher { books }
        }

    companion object {
        private val books: List<Map<String, String>?> = listOf(
            ImmutableMap.of(
                "id", "book-1",
                "name", "Harry Potter and the Philosopher's Stone",
                "pageCount", "223",
                "authorId", "author-1"
            ),
            ImmutableMap.of(
                "id", "book-2",
                "name", "Moby Dick",
                "pageCount", "635",
                "authorId", "author-2"
            ),
            ImmutableMap.of(
                "id", "book-3",
                "name", "Interview with the vampire",
                "pageCount", "371",
                "authorId", "author-3"
            )
        )
        private val authors: List<Map<String, String>?> = listOf(
            ImmutableMap.of(
                "id", "author-1",
                "firstName", "Joanne",
                "lastName", "Rowling"
            ),
            ImmutableMap.of(
                "id", "author-2",
                "firstName", "Herman",
                "lastName", "Melville"
            ),
            ImmutableMap.of(
                "id", "author-3",
                "firstName", "Anne",
                "lastName", "Rice"
            )
        )
    }
}