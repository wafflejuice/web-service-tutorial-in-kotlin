package org.wafflejuice.springboot.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.wafflejuice.springboot.domain.posts.PostsRepository
import org.wafflejuice.springboot.service.posts.PostsService

@Controller
class IndexController(
    val postsService: PostsService
) {
    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("posts", postsService.findAllDesc())
        return "index"
    }

    @GetMapping("/posts/save")
    fun postsSave(): String {
        return "posts-save"
    }
}