package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BlogCommentEntity;
import com.example.demo.service.BlogCommentService;

@RestController
@RequestMapping("/blog-comment")
public class BlogCommentController {

	@Autowired
	private BlogCommentService service;

	@PostMapping("/")
	public ResponseEntity<BlogCommentEntity> create(@RequestBody BlogCommentEntity comment) {
		BlogCommentEntity blogCommentEntity = service.create(comment);
		
		return new ResponseEntity<>(blogCommentEntity, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{blogId}")
	public ResponseEntity<List<BlogCommentEntity>> list(@PathVariable Integer blogId) {
		List<BlogCommentEntity> blogCommentEntities = service.list(blogId);
		
		return new ResponseEntity<>(blogCommentEntities, HttpStatus.OK);
	}
}