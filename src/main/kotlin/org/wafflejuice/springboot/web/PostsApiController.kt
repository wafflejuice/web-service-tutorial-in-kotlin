package org.wafflejuice.springboot.web

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.wafflejuice.springboot.service.posts.PostsService
import org.wafflejuice.springboot.web.dto.PostsSaveRequestDto

@RestController
class PostsApiController(
    private val postsService: PostsService
) {
    @PostMapping("/api/v1/posts")
    fun save(@RequestBody requestDto: PostsSaveRequestDto): Long? = postsService.save(requestDto)
}