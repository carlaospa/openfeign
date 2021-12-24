package com.carlaospa.openfeign.client;

import com.carlaospa.openfeign.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "post", url = "https://jsonplaceholder.typicode.com")
public interface PostClient {

    @GetMapping(value = "/posts")
    List<PostDTO> getAllPosts();

}
