package com.neshan.navigate_neshan.Repository;

import com.neshan.navigate_neshan.Data.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,Long> {
    UserInfo findUserByEmail(String email);
}
