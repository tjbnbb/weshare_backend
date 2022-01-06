package com.example.service;
import com.example.entity.Post;
import com.example.entity.UserInfo;
import com.example.repo.PostRepo;
import com.example.repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepo PostRepository;
    @Autowired
    private UserInfoRepo userProfileRepository;
    public String creat(Post post) {

        PostRepository.save(post);
        UserInfo userinfo=userProfileRepository.findUserProfileByinfoId(post.getInfoId());
        userinfo.setPostCount(userinfo.getPostCount()+1);
        userProfileRepository.save(userinfo);
        return "Post successfully!";
    }
    public List<Post> getAll() {
        return PostRepository.findAll(Sort.by(Direction.DESC, "createdAt"));
    }
    public List<Post> getByInfoId(long infoId) { return PostRepository.findAllByInfoId(infoId, Sort.by(Direction.DESC, "createdAt")); }
    public Integer deleteByPostId(long postid) { return PostRepository.deletePostByPostid(postid); }
}
