package com.jaehong.entity

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
@Table
data class Author (
        @Id
        @GeneratedValue
        val id: Long,

        val firstName: String,

        val lastName: String
)

interface AuthorRepository : JpaRepository<Author, Long>