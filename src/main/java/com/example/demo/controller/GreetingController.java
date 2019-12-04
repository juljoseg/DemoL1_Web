package com.example.demo.controller;

import java.awt.PageAttributes.MediaType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Message;

@Controller
public class GreetingController {

	
  @Autowired
  private RestTemplate restTemplate;
	
  @Value("${message.service.url}")
   private String url;
	
  @GetMapping("/message")
  public String messageForm(Model model) {
    
	model.addAttribute("message", new Message());
    
	
    
    return "greeting";
  }

  @PostMapping("/submitMessage")
  public String messageSubmit(@ModelAttribute Message message) {
	  
	  Map<String, String> params = new HashMap<String, String>();
	    params.put("text", message.getText());
		
	    restTemplate.put(url, message);
	    
	  
    return "greeting";
  }
  
  
//  @GetMapping("/message")
//  public String greeting() {
//   
//	 
//	  
//    
//       String result =  restTemplate.getForObject(
//				"http://localhost:8081/message?text=yo", String.class);
//	  
//       System.out.println("------ " + result);
//       
//    return "";
//  }

}
