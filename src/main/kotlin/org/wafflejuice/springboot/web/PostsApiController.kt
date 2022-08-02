package org.wafflejuice.springboot.web

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.wafflejuice.springboot.service.posts.PostsService
import org.wafflejuice.springboot.web.dto.PostsSaveRequestDto
import org.wafflejuice.springboot.web.dto.PostsUpdateRequestDto

@RestController
class PostsApiController(
    private val postsService: PostsService
) {
    @PostMapping("/api/v1/posts")
    fun save(@RequestBody requestDto: PostsSaveRequestDto): Long? = postsService.save(requestDto)

    @PutMapping("/api/v1/posts/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody requestDto: PostsUpdateRequestDto
    ) = postsService.update(id, requestDto)

    @DeleteMapping("/api/v1/posts/{id}")
    fun delete(
        @PathVariable id: Long
    ): Long {
        postsService.delete(id)
        return id
    }

    @GetMapping("/api/v1/posts/{id}")
    fun findById(
        @PathVariable id: Long
    ) = postsService.findById(id)
}