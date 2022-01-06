package com.example.controller;

import com.example.entity.Post;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService PostService;
    @GetMapping("/getAll")
    public List<Post> getAll(){
        return PostService.getAll();
    }
    @PostMapping(value="/create")
    public String create(@RequestBody Post post) {
        return PostService.creat(post);
    }
    @GetMapping("/getByInfoId")
    public List<Post> getByInfoId(@RequestParam("infoId") long infoId) {
        return PostService.getByInfoId(infoId);
    }
    @DeleteMapping("/deleteByPostId")
    public Integer deleteByPostId(@RequestParam("postid") long postid) { return PostService.deleteByPostId(postid); }
}

