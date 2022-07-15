package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BlogEntity;
import com.example.demo.service.BlogService;

@RestController
@RequestMapping("/blogs")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@GetMapping("/")
	public ResponseEntity<List<BlogEntity>> getAllBlogs() {
		List<BlogEntity> blogEntities = blogService.getAllBlogs();
        return new ResponseEntity<>(blogEntities, HttpStatus.OK);
    }
	
	
	@PostMapping("/")
	public ResponseEntity<BlogEntity> create(@RequestBody BlogEntity blog) {
        return new ResponseEntity<>(blogService.create(blog), HttpStatus.CREATED);
    }
}
