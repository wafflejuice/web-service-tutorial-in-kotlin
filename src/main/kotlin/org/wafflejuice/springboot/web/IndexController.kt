package org.wafflejuice.springboot.web

import org.springframework.web.bind.annotation.GetMapping

class IndexController {
    @GetMapping("/")
    fun index(): String {
        return "index"
    }
}