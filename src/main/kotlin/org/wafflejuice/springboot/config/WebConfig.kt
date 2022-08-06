package org.wafflejuice.springboot.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.wafflejuice.springboot.config.auth.LoginUserArgumentResolver

@Configuration
class WebConfig(
    val loginUserArgumentResolver: LoginUserArgumentResolver
): WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(loginUserArgumentResolver)
    }
}