package org.wafflejuice.springboot.domain.posts

import org.springframework.data.jpa.repository.JpaRepository

interface PostsRepository : JpaRepository<Posts, Long>