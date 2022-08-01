package org.wafflejuice.springboot.service.posts

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.wafflejuice.springboot.domain.posts.PostsRepository
import org.wafflejuice.springboot.web.dto.PostsListResponseDto
import org.wafflejuice.springboot.web.dto.PostsResponseDto
import org.wafflejuice.springboot.web.dto.PostsSaveRequestDto
import org.wafflejuice.springboot.web.dto.PostsUpdateRequestDto
import java.lang.IllegalArgumentException
import java.util.stream.Collectors

@Service
class PostsService(
    private val postsRepository: PostsRepository
) {
    @Transactional
    fun save(
        requestDto: PostsSaveRequestDto
    ): Long? = postsRepository.save(requestDto.toEntity()).id

    @Transactional
    fun update(
        id: Long,
        requestDto: PostsUpdateRequestDto
    ): Long {
        val posts = postsRepository.findById(id)
            .orElseThrow { IllegalArgumentException("해당 게시글이 없습니다. id=${id}") }

        posts.update(requestDto.title, requestDto.content)

        return id
    }

    fun findById(
        id: Long
    ): PostsResponseDto {
        val entity = postsRepository.findById(id)
            .orElseThrow { IllegalArgumentException("해당 게시글이 없습니다. id=${id}") }

        return PostsResponseDto(entity)
    }

    @Transactional(readOnly = true)
    fun findAllDesc(): List<PostsListResponseDto> {
        return postsRepository.findAllDesc()
            .map { PostsListResponseDto(it) }
            .toList()
    }
}