package com.example.demo.controller

import com.example.demo.service.MemoService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("greeting")
class GreetingController(private val service: MemoService) {

    private val log = LoggerFactory.getLogger(MemoController::class.java)

    @GetMapping("/hello")
    fun hello(
            @RequestParam(value = "name", required = false, defaultValue = "world") name: String,
            model: Model): String {
        model.addAttribute("name", name)
        return "greeting"
    }

    @GetMapping("/hello2")
    fun hello2(
            @RequestParam(value = "id", required = false, defaultValue = "1") name: Long,
            page: Pageable,
            model: Model): String {

        val id : Long = name.toLong()
        val memo = service.findById(id)
        val memos = service.findAll(page)

        log.debug("store memo : {}", memo)

        model.addAttribute("name", name)
        model.addAttribute("memo", memo)
        model.addAttribute("memos", memos)
        return "greeting"
    }

}