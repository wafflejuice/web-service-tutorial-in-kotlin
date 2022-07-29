package org.wafflejuice.springboot.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class IndexControllerTest(
    @Autowired
    val restTemplate: TestRestTemplate
) {
    @Test
    fun `메인페이지 로딩`() {
        //when
        val body = restTemplate.getForObject("/", String::class.java)

        //then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스")
    }
}