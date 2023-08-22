package com.bunny

import com.bunny.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
//        application {
//            configureRouting()
//        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}

class Applicationdemo{
    fun testRoot() = testApplication {
//        application {
//            configureRouting()
//        }
        println("hi this is application demo")
    }
}
