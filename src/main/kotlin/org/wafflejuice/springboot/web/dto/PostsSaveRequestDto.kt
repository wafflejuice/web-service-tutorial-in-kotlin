package org.wafflejuice.springboot.web.dto

import org.wafflejuice.springboot.domain.posts.Posts

data class PostsSaveRequestDto(
    val title: String,
    val content: String,
    val author: String
) {
    fun toEntity(): Posts = Posts(title = title, content = content, author = author)
}