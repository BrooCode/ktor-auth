package com.bunny

import com.bunny.data.user.MongoUserDataSource
import com.bunny.data.user.User
import com.bunny.plugins.*
import com.bunny.security.hashing.SHA256HashingService
import com.bunny.security.token.JwtTokenService
import com.bunny.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.netty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
//    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        println("imhere")
        val mongoPw = System.getenv("MONGO_PW")
        val dbName = "ktor-auth"
        val db = KMongo.createClient(
            connectionString = "mongodb+srv://darkbillo4758:Rahul4758@cluster0.jrsq6ze.mongodb.net/$dbName?retryWrites=true&w=majority"
        ).coroutine
            .getDatabase(dbName)

        val userDataSource = MongoUserDataSource(db)
        val tokenService = JwtTokenService()
        val tokenConfig = TokenConfig(
            issuer = this.environment.config.property("jwt.issuer").getString(),
            audience = this.environment.config.property("jwt.audience").getString(),
            expiresIn = 365L * 1000L * 60L * 60L * 24L,
//            secret = System.getenv("JWT_SECRET")
            secret = "jwt-secret"
        )
        val hashingService = SHA256HashingService()

        configureSecurity(tokenConfig)
        configureRouting(userDataSource, hashingService, tokenService, tokenConfig)
        configureSerialization()
        configureMonitoring()

//    }.start(wait = true)
}

