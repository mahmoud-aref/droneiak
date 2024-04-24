package com.elmenus.infrastructure.datasource.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(private val userEntity: UserEntity) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.userEntity.getAuthorities()
    }

    override fun getPassword(): String {
        return this.userEntity.user.password
    }

    override fun getUsername(): String {
        return this.userEntity.user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return this.userEntity.user.active
    }

    override fun isAccountNonLocked(): Boolean {
        return this.userEntity.user.active
    }

    override fun isCredentialsNonExpired(): Boolean {
        return this.userEntity.user.active
    }

    override fun isEnabled(): Boolean {
        return this.userEntity.user.active
    }
}