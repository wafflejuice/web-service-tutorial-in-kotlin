package org.wafflejuice.springboot.web.domain.posts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.wafflejuice.springboot.domain.posts.Posts
import org.wafflejuice.springboot.domain.posts.PostsRepository

@ExtendWith(SpringExtension::class)
@SpringBootTest
class PostsRepositoryTest(
    @Autowired val postsRepository: PostsRepository
) {
    @AfterEach
    fun cleanup() {
        postsRepository.deleteAll()
    }

    @Test
    fun `게시글저장 불러오기`() {
        //given
        val title = "테스트 게시글"
        val content = "테스트 본문"
        val author = "테스트 저자"

        postsRepository.save(Posts(title = title, content = content, author = author))

        //when
        val postsList = postsRepository.findAll()

        //then
        val posts = postsList[0]
        assertThat(posts.title).isEqualTo(title)
        assertThat(posts.content).isEqualTo(content)
        assertThat(posts.author).isEqualTo(author)
    }
}