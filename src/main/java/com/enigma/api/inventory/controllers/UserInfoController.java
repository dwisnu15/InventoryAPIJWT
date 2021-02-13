package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.UserInfo;
import com.enigma.api.inventory.repositories.UserInfoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
public class UserInfoController {

    final private UserInfoRepository uinfoRepository;

    public UserInfoController(UserInfoRepository uinforepository) {
        this.uinfoRepository = uinforepository;
    }

    @PostMapping("/users")
    public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
        String username = body.get("username");
        if (uinfoRepository.existsByUsername(username)){
            throw new ValidationException("Username already existed");
        }

        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        //the same as
//        String hashedPassword = hashData.get_SHA_512_SecurePassword(password);
        String fullname = body.get("fullname");
        uinfoRepository.save(new UserInfo(fullname, username, encodedPassword));
        return true;
    }
}
