package com.example.controller;

import com.example.entity.User;
import com.example.entity.UserInfo;
import com.example.repo.UserInfoRepo;
import com.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class UserInfoController {
    @Autowired
    UserInfoRepo userProfileRepository;
    @Autowired
    UserRepo userRepository;

    @GetMapping("/findAll")
    public List<UserInfo> findAll(){
        return userProfileRepository.findAll();
    }

    @GetMapping("/findByEmail")
    public UserInfo findProfileByEmail(@RequestParam("email") String email){
        return userProfileRepository.findUserProfileByEmail(email);
    }
    @GetMapping("/findByinfoId")
    public UserInfo findProfileByprofileId(@RequestParam("infoId") long infoId){
        return userProfileRepository.findUserProfileByinfoId(infoId);
    }


    @GetMapping("/findByUsernameLike")
    public List<UserInfo> findByUsernameLike(@RequestParam("username") String usernmae){
        return userProfileRepository.findAllByUsernameContains(usernmae);
    }

    @PostMapping(value="/save")
    public String save(@RequestBody UserInfo userProfile) {
        User user=userRepository.findByEmail(userProfile.getEmail());

        if(user!=null){
            user.setUsername(userProfile.getUsername());//更新用户名
            //userRepository.deleteUserByEmail(user.getEmail());
            userRepository.save(user);                  //更新user

            //userProfileRepository.deleteUserProfileByEmail(userProfile.getEmail());
            userProfileRepository.save(userProfile);    //更新userProfile
            return "sucess";
        }
        else
            return "User Don't Exit, Please Register First!";
    }

    @PostMapping(value="/update")
    public String update(@RequestBody UserInfo userProfile) {
        User user=userRepository.findByEmail(userProfile.getEmail());
        UserInfo userinfo=userProfileRepository.findUserProfileByEmail(userProfile.getEmail());
        UserInfo userinfo_exist=userProfileRepository.findUserProfileByUsername(userProfile.getUsername());
        if(user!=null){
            if(userinfo_exist==null||userinfo.getInfoId()==userinfo_exist.getInfoId()) {//要更新的用户名不存在
                if (userProfile.getUsername() != null)
                    user.setUsername(userProfile.getUsername());//更新用户名
                //userRepository.deleteUserByEmail(user.getEmail());
                userRepository.save(user);                  //更新user
                if (userProfile.getUsername() != null)
                    userinfo.setUsername(userProfile.getUsername());
                if (userProfile.getName() != null)
                    userinfo.setName(userProfile.getName());
                if (userProfile.getAge() != null)
                    userinfo.setAge(userProfile.getAge());
                if (userProfile.getJob() != null)
                    userinfo.setJob(userProfile.getJob());
                if (userProfile.getPhone() != null)
                    userinfo.setPhone(userProfile.getPhone());
                userProfileRepository.save(userinfo);    //更新userProfile
                return "succeed!";
            }
            return "The username has been used!";
        }
        else
            return "User Don't Exit, Please Register First!";
    }

    @GetMapping("/delete")
    public Integer deleteUserProfileByEmail(@RequestParam("email") String email){
        userRepository.deleteUserByEmail(email);
        return  userProfileRepository.deleteUserProfileByEmail(email);
    }

}
