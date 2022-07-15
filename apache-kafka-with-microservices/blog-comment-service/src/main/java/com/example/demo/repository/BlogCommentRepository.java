package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BlogCommentEntity;

public interface BlogCommentRepository extends JpaRepository<BlogCommentEntity, Integer> {
	List<BlogCommentEntity> findAllByBlogId(Integer blogId);
}