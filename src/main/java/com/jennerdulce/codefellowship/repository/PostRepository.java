package com.jennerdulce.codefellowship.repository;

import com.jennerdulce.codefellowship.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
