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
    }
}
