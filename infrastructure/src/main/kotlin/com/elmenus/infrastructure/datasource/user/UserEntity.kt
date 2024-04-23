package com.elmenus.infrastructure.datasource.user

import com.elmenus.domain.user.model.User
import com.elmenus.domain.user.model.UserRole
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Document
data class UserEntity(
    val user: User
) : UserDetails {

    companion object {
        fun fromRequest(
            username: String,
            password: String,
            fullName: String,
            roles: List<String>
        ): UserEntity {

            val rolesEnum = roles
                .stream()
                .map { role -> UserRole.valueOf(role) }
                .toList()

            return UserEntity(
                user = User(
                    UUID.randomUUID(),
                    username = username,
                    password = password,
                    fullName = fullName,
                    roles = rolesEnum,
                    active = true
                )
            )
        }

        fun getRoles(userEntity: UserEntity): List<String> {
            return userEntity.user.roles
                .stream().map { it.toString() }.toList()
        }

        fun getId(userEntity: UserEntity): String {
            return userEntity.user.id.toString()
        }

        fun getFullName(userEntity: UserEntity): String {
            return userEntity.user.fullName
        }


    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.singletonList(GrantedAuthority { user.roles.toString() })
    }

    override fun getPassword(): String {
        return this.user.password
    }

    override fun getUsername(): String {
        return this.user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return this.user.active
    }

    override fun isAccountNonLocked(): Boolean {
        return this.user.active
    }

    override fun isCredentialsNonExpired(): Boolean {
        return this.user.active
    }

    override fun isEnabled(): Boolean {
        return this.user.active
    }
}
