package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.kafkaevent.ProducerService;
import com.example.demo.model.BlogCommentEntity;
import com.example.demo.repository.BlogCommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogCommentService {
	private final ProducerService producerService;
	private final BlogCommentRepository repository;

	public BlogCommentEntity create(BlogCommentEntity comment) {
		BlogCommentEntity blogCommentEntity = repository.save(comment);
		producerService.sendMessage("This " + blogCommentEntity.toString() + " comment is created !");
		return blogCommentEntity;
	}

	public List<BlogCommentEntity> list(Integer blogId) {
		return repository.findAllByBlogId(blogId);
	}
}