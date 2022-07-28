package org.wafflejuice.springboot.domain.posts

import javax.persistence.*

@Entity
data class Posts(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 500, nullable = false)
    var title: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    var content : String,

    val author: String
) {
    fun update(
        title: String,
        content: String
    ) {
        this.title = title
        this.content = content
    }
}