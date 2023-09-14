package com.neshan.navigate_neshan.Repository;

import com.neshan.navigate_neshan.Model.Report;
import com.neshan.navigate_neshan.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
}
