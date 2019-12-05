package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Message;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class GreetingController {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${message.service.url}")
    private String messageUrl;

    @Value("${user.get.service.url}")
    private String userGetUrl;



    @GetMapping("/message")
    public String messageForm(Model model, @RequestParam(value="name") String name) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(userGetUrl).queryParam("name",name);

        User user = restTemplate.getForObject(builder.toUriString(), User.class);

        model.addAttribute("user", user);

        model.addAttribute("message", new Message());

        return "greeting";
    }

    @GetMapping("/submitMessage")
    public String messageSubmit(@ModelAttribute Message message,@ModelAttribute User user) {

        restTemplate.put(messageUrl, message);

        return "result";
    }


}
