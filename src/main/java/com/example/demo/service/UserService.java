package com.example.demo.service;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.beans.Transient;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.example.demo.converter.UserConverter.convertUser;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDTO getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return convertUser(user);
    }

    public Long addNewUser(UserDTO userDTO) {
        List<User> userList = userRepository.findByEmail(userDTO.getEmail());
        if (!CollectionUtils.isEmpty(userList)) {
            throw new IllegalStateException("email:" + userDTO.getEmail() + "has been used!");
        }
        User user = UserConverter.convertUser(userDTO);
        user.setCreateTime(new Date());
        user = userRepository.save(user);
        return user.getId();
    }

    public void deleteUserById(long id) {
        userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id:" + id + "doesn't exist!"));
        userRepository.deleteById(id);
    }

    @Transactional // 执行update操作，失败时自动回滚
    public UserDTO updateUserById(long id, String name, String email) {
        User userInDB = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id:" + id + "doesn't exist!"));
        if (StringUtils.hasLength(name) && !userInDB.getName().equals(name)) {
            userInDB.setName(name);
        }
        if (StringUtils.hasLength(email) && !userInDB.getEmail().equals(email)) {
            userInDB.setEmail(email);
        }
        userInDB.setUpdateTime(new Date());
        User user = userRepository.save(userInDB);
        return convertUser(user);
    }

}
