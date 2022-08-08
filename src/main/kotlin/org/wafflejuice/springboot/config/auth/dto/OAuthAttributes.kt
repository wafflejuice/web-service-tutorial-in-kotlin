package org.wafflejuice.springboot.config.auth.dto

import org.wafflejuice.springboot.domain.user.Role
import org.wafflejuice.springboot.domain.user.User

class OAuthAttributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val name: String,
    val email: String,
    val picture: String
) {
    fun toEntity(): User {
        return User(
            name = name,
            email = email,
            picture = picture,
            role = Role.GUEST
        )
    }

    companion object {
        fun of(
            registrationId: String,
            userNameAttributeName: String,
            attributes: Map<String, Any>
        ): OAuthAttributes {
            if (registrationId == "naver") {
                return ofNaver("id", attributes)
            }

            return ofGoogle(userNameAttributeName, attributes)
        }

        fun ofGoogle(
            userNameAttributeName: String,
            attributes: Map<String, Any>
        ): OAuthAttributes {
            return OAuthAttributes(
                name = attributes["name"].toString(),
                email = attributes["email"].toString(),
                picture = attributes["picture"].toString(),
                attributes = attributes,
                nameAttributeKey = userNameAttributeName
            )
        }

        fun ofNaver(
            userNameAttributeName: String,
            attributes: Map<String, Any>
        ): OAuthAttributes {
            val response: Map<String, Any> = attributes["response"] as Map<String, Any>

            return OAuthAttributes(
                name = response["name"] as String,
                email = response["email"] as String,
                picture = response["profile_image"] as String,
                attributes = response,
                nameAttributeKey = userNameAttributeName
            )
        }
    }
}
