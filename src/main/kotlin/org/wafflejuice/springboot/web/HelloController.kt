package org.wafflejuice.springboot.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.wafflejuice.springboot.web.dto.HelloResponseDto

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/hello/dto")
    fun helloDto(
        @RequestParam("name") name: String,
        @RequestParam("amount") amount: Int
    ): HelloResponseDto {
        return HelloResponseDto(name, amount)
    }
}