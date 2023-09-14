package com.neshan.navigate_neshan.Service;

import com.neshan.navigate_neshan.Dto.UserDto;
import com.neshan.navigate_neshan.Mapper.UserMapper;
import com.neshan.navigate_neshan.Model.User;
import com.neshan.navigate_neshan.Repository.UserRepo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepo userRepo;
    private String cacheName = "userCache";

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDto findUser(String email) {
        return UserMapper.INSTANCE.userToUserDTO(userRepo.findUserByEmail(email));
    }

    public void save(User user) {
        userRepo.save(user);
    }

}