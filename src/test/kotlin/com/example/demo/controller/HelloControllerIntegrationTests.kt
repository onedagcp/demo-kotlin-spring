package com.example.demo.controller

import com.example.demo.DemoKotlinApplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import java.nio.charset.Charset

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [DemoKotlinApplication::class],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerIntegrationTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    private val contentType = MediaType(MediaType.TEXT_PLAIN.type,
            MediaType.TEXT_PLAIN.subtype, Charset.forName("utf8"))

    @Test
    fun helloWorld() {
        val result = testRestTemplate.getForEntity("/hello/world", String::class.java)
        println(result)

        assertThat(result).isNotNull()
        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(result.headers.contentType).isEqualTo(contentType)
        assertThat(result.body).isEqualTo("hello world!")
    }

}