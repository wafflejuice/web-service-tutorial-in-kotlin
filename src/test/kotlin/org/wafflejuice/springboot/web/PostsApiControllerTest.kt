package org.wafflejuice.springboot.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.wafflejuice.springboot.domain.posts.PostsRepository
import org.wafflejuice.springboot.web.dto.PostsSaveRequestDto

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class PostsApiControllerTest(
    @LocalServerPort
    private val port: Int,

    @Autowired
    private val restTemplate: TestRestTemplate,

    @Autowired
    private val postsRepository: PostsRepository
) {
    @AfterEach
    fun tearDown() = postsRepository.deleteAll()

    @Test
    fun `Posts 등록`() {
        //given
        val title = "title"
        val content = "content"
        val author = "author"

        val requestDto = PostsSaveRequestDto(title = title, content = content, author = author)
        val url = "http://localhost:${port}/api/v1/posts"

        //when
        val responseEntity: ResponseEntity<Long> = restTemplate.postForEntity(url, requestDto, Long::javaClass)

        //then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body ?: throw Exception("Reponse with null body")).isGreaterThan(0L)

        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(title)
        assertThat(all[0].content).isEqualTo(content)
        assertThat(all[0].author).isEqualTo(author)
    }
}