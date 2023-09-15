package com.neshan.navigate_neshan.Service;

import com.neshan.navigate_neshan.Dto.UserDto;
import com.neshan.navigate_neshan.Mapper.UserMapper;
import com.neshan.navigate_neshan.Model.User;
import com.neshan.navigate_neshan.Repository.UserRepo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepo userRepo;
    RedissonClient redissonClient;
    RMap<String, UserDto> userCache;
    String cacheName = "userCache";

    public UserService(UserRepo userRepo, RedissonClient redissonClient) {
        this.userRepo = userRepo;
        this.redissonClient = redissonClient;
        userCache = redissonClient.getMap(cacheName);
    }

    public UserDto findUser(String email) {
        if (userCache.containsKey(email)) {
            return userCache.get(email);
        }
        // If not cached, fetch from the repository and cache it
        User user = userRepo.findUserByEmail(email);
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);

        // Store the result in the cache
        userCache.put(email, userDto);

        return userDto;
    }

    public void save(User user) {
        userCache.remove(user.getEmail());
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(userRepo.save(user));
        userCache.put(user.getEmail(), userDto);
    }

}