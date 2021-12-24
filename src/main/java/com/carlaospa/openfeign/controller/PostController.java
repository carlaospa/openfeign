package com.carlaospa.openfeign.controller;

import com.carlaospa.openfeign.client.PostClient;
import com.carlaospa.openfeign.dto.PostDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("posts")
public class PostController {

    private PostClient postClient;

    @GetMapping
    public List<PostDTO> getAllPost(){
        return postClient.getAllPosts();
    }
}
