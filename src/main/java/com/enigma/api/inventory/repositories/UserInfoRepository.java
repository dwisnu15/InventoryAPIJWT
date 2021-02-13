package com.enigma.api.inventory.repositories;

import com.enigma.api.inventory.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    Boolean existsByUsername(String username);
    UserInfo findByUsername(String username);
}
