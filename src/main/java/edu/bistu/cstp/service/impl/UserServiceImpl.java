package edu.bistu.cstp.service.impl;

import edu.bistu.cstp.dao.entity.User;
import edu.bistu.cstp.dao.repository.UserRepository;
import edu.bistu.cstp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService
{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isLoginInfoValid(String username, String pw)
    {
        User user = userRepository.findUserByUsernameAndPw(username, pw);
        if(user != null)
            return true;
        else
            return false;
    }
}
