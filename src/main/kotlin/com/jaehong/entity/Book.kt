package com.jaehong.entity

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
@Table
data class Book (
        @Id
        @GeneratedValue
        val id: Long,

        val name: String,

        val pageCount: Int,

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "author_id")
        val author: Author
)

interface BookRepository : JpaRepository<Book, Long>