package org.wafflejuice.springboot.domain.posts

import org.wafflejuice.springboot.domain.BaseTimeEntity
import javax.persistence.*

@Entity
data class Posts(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 500, nullable = false)
    var title: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    var content: String,

    val author: String
) : BaseTimeEntity() {
    fun update(
        title: String,
        content: String
    ) {
        this.title = title
        this.content = content
    }
}