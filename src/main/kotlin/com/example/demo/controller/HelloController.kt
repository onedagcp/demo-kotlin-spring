package com.example.demo.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * this code is investigation.
 */
@RestController
@RequestMapping(path = ["hello"], produces = [MediaType.TEXT_PLAIN_VALUE])
class HelloController {

    private val log = LoggerFactory.getLogger(HelloController::class.java)

    @Autowired
    lateinit var env: Environment

    @GetMapping(path = ["world"])
    fun greeting(): String {
        env.getProperty("my-app.my-module.foo.first-name")?.let { println(it) }
        env.getProperty("java_home")?.let { println(it) }

        return "hello world!"
    }

}
