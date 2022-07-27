package org.wafflejuice.springboot.service.posts

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.wafflejuice.springboot.domain.posts.PostsRepository
import org.wafflejuice.springboot.web.dto.PostsSaveRequestDto

@Service
class PostsService(
    private val postsRepository: PostsRepository
) {
    @Transactional
    fun save(requestDto: PostsSaveRequestDto): Long? = postsRepository.save(requestDto.toEntity()).id
}