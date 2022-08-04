package org.wafflejuice.springboot.domain.user

import org.wafflejuice.springboot.domain.BaseTimeEntity
import javax.persistence.*

@Entity(name = "USER_")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    val email: String,

    @Column
    var picture: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role
) : BaseTimeEntity() {
    fun update(name: String, picture: String): User {
        this.name = name
        this.picture = picture

        return this
    }

    fun getRoleKey() = this.role.key
}