package org.wafflejuice.springboot.domain.posts

import javax.persistence.*

@Entity
data class Posts(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 500, nullable = false)
    val title: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    val content : String,

    val author: String
)