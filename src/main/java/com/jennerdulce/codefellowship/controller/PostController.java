package com.jennerdulce.codefellowship.controller;

import com.jennerdulce.codefellowship.model.ApplicationUser;
import com.jennerdulce.codefellowship.model.Post;
import com.jennerdulce.codefellowship.repository.ApplicationUserRepository;
import com.jennerdulce.codefellowship.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/create-post")
    public RedirectView createPost(String username, String content) {
        ApplicationUser currentUser = applicationUserRepository.findByUsername(username);
        LocalDateTime timestamp = LocalDateTime.now();
        Post newPost = new Post(content, currentUser, timestamp);
        List<Post> posts = currentUser.getUserPosts();
        if(posts == null){
            posts = new ArrayList<>();
        }
        posts.add(newPost);
        applicationUserRepository.save(currentUser);
        String route = "/user/" + username;
        return new RedirectView(route);
    }

}
