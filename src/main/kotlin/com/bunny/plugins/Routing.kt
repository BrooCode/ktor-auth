package com.bunny.plugins

import com.bunny.authenticate
import com.bunny.data.user.UserDataSource
import com.bunny.getSecretInfo
import com.bunny.security.hashing.HashingService
import com.bunny.security.token.TokenConfig
import com.bunny.security.token.TokenService
import com.bunny.signIn
import com.bunny.signUp
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    routing {
        signIn(userDataSource, hashingService, tokenService, tokenConfig)
        signUp(hashingService, userDataSource)
        authenticate()
        getSecretInfo()
    }
}
