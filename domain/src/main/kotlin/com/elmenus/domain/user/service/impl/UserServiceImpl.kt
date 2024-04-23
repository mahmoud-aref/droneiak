package com.elmenus.domain.user.service.impl

import com.elmenus.domain.user.exception.UserNameExistsException
import com.elmenus.domain.user.exception.UserNotFoundException
import com.elmenus.domain.user.repository.UserRepository
import com.elmenus.domain.user.model.User
import com.elmenus.domain.user.service.UserService
import java.util.UUID
import java.util.concurrent.CompletableFuture

class UserServiceImpl : UserService {

    private lateinit var userRepository: UserRepository

    override fun createUser(user: User): CompletableFuture<User> {
        return userRepository.existsByUsername(user.username)
            .thenCompose { exists ->
                if (exists) {
                    CompletableFuture.failedFuture(UserNameExistsException())
                } else {
                    userRepository.save(user)
                }
            }
    }

    override fun getUserById(id: UUID): CompletableFuture<User> {
        return userRepository.findById(id)
            .thenApply { optionalUser ->
                optionalUser.orElseThrow { UserNotFoundException() }
            }
    }

    override fun getUserByUsername(username: String): CompletableFuture<User> {
        return userRepository.findByUsername(username)
            .thenApply { optionalUser ->
                optionalUser.orElseThrow { UserNotFoundException() }
            }
    }

    override fun getAllUsers(): CompletableFuture<List<User>> {
        return userRepository.findAll()
            .thenApply { optionalUsers ->
                optionalUsers.orElseThrow { UserNotFoundException() }
            }
    }

    override fun updateUser(user: User): CompletableFuture<User> {
        return userRepository.save(user)
    }

    override fun updatePassword(username: String, password: String): CompletableFuture<User> {
        return userRepository.findByUsername(username)
            .thenApply { optionalUser ->
                val user = optionalUser.orElseThrow { UserNotFoundException() }
                user.updatePassword(password)
            }
            .thenCompose { user ->
                userRepository.save(user)
            }
    }

    override fun deleteUser(user: User): CompletableFuture<Boolean> {
        return userRepository.delete(user)
    }


}