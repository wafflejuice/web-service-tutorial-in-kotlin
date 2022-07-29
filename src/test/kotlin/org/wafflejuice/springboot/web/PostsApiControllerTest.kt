package org.wafflejuice.springboot.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.wafflejuice.springboot.domain.posts.Posts
import org.wafflejuice.springboot.domain.posts.PostsRepository
import org.wafflejuice.springboot.web.dto.PostsSaveRequestDto
import org.wafflejuice.springboot.web.dto.PostsUpdateRequestDto
import java.time.LocalDateTime

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

    @Test
    fun `Posts 수정`() {
        //given
        val savedPosts = postsRepository.save(Posts(title = "title", content = "content", author = "author"))

        val updateId = savedPosts.id
        val expectedTitle = "title2"
        val expectedContent = "content2"

        val requestDto = PostsUpdateRequestDto(title = expectedTitle, content = expectedContent)
        val url = "http://localhost:${port}/api/v1/posts/${updateId}"

        val requestEntity = HttpEntity<PostsUpdateRequestDto>(requestDto)

        //when
        val responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long::class.java)

        //then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body ?: throw Exception("Reponse with null body")).isGreaterThan(0L)

        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(expectedTitle)
        assertThat(all[0].content).isEqualTo(expectedContent)
    }

    @Test
    fun `BaseTimeEntity 등록`() {
        //given
        val now = LocalDateTime.of(2019, 6, 4, 0, 0, 0)
        postsRepository.save(Posts(title = "title", content = "content", author = "author"))

        //when
        val postsList = postsRepository.findAll()

        //then
        val posts = postsList[0]
        println("> createDate=${posts.createdDate}, modifiedDate=${posts.modifiedDate}")
        assertThat(posts.createdDate).isAfter(now)
    }

    @Test
    fun `BaseTimeEntity 수정`() {
        //given
        postsRepository.save(Posts(title = "title", content = "content", author = "author"))

        val postsList = postsRepository.findAll()
        val posts = postsList[0]
        posts.content = "updated_content"
        postsRepository.save(posts)

        println("> createDate=${posts.createdDate}, modifiedDate=${posts.modifiedDate}") // 동일

        //when
        val updatedPostsList = postsRepository.findAll()

        //then
        val updatedPosts = updatedPostsList[0]
        println("> createDate=${updatedPosts.createdDate}, modifiedDate=${updatedPosts.modifiedDate}") // 비동일
        assertThat(updatedPosts.modifiedDate).isAfter(updatedPosts.createdDate)
    }
}