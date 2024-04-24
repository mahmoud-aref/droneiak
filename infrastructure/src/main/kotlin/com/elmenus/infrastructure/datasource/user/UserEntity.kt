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
) {

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

        fun getUsername(userEntity: UserEntity): String {
            return userEntity.user.username
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

    fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.singletonList(GrantedAuthority { user.roles.toString() })
    }

}
