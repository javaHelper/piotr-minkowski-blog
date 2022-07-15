package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BlogEntity;
import com.example.demo.kafkaevent.ProducerService;
import com.example.demo.repository.BlogRepository;


@Transactional
@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ProducerService producerService;
	
	public BlogEntity create(BlogEntity blog) {
		BlogEntity blogEntity = blogRepository.save(blog);
        producerService.sendMessage("This " + blogEntity.toString() + " blog is created !");
        return blogRepository.save(blogEntity);
    }

    public List<BlogEntity> getAllBlogs() {
        return blogRepository.findAll();
    }
}
