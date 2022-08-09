package org.wafflejuice.springboot.web

import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.wafflejuice.springboot.config.auth.SecurityConfig

//@Disabled("Application에 @EnableJpaAuditing annotation 사용시 테스트 실패")
@ExtendWith(SpringExtension::class)
@WebMvcTest(
    HelloController::class,
    excludeFilters = [
        ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = [SecurityConfig::class])
    ]
)
internal class HelloControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @WithMockUser(roles = ["USER"])
    @Test
    fun `hello가 리턴된다`() {
        val hello = "hello"

        mvc.perform(get("/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string(hello))
    }

    @WithMockUser(roles = ["USER"])
    @Test
    fun `helloDto가 리턴된다`() {
        val name = "hello"
        val amount = 1000

        mvc.perform(
            get("/hello/dto")
                .param("name", name)
                .param("amount", amount.toString()))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name", equalTo(name)))
            .andExpect(jsonPath("$.amount", equalTo(amount)))
    }
}