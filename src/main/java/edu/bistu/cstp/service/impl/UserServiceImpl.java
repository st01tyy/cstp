package edu.bistu.cstp.service.impl;

import edu.bistu.cstp.dao.entity.User;
import edu.bistu.cstp.dao.repository.UserRepository;
import edu.bistu.cstp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
    public User isLoginInfoValid(String username, String pw)
    {
        User user = userRepository.findUserByUsernameAndPw(username, DigestUtils.md5DigestAsHex(pw.getBytes()));
        return user;
    }

    @Override
    public User createNewUser(String username, String pw)
    {
        /**
         * 规定：返回结果为null，为发生未知错误；返回结果中，id为0，为用户名重复
         */
        User temporaryUser = createTemporaryUser(username, pw);
        User result;
        try
        {
            result = userRepository.save(temporaryUser);
        }
        catch (DataIntegrityViolationException e)
        {
            result = onDuplicateError();
        }
        catch (Exception e)
        {
            result = null;
        }
        return result;
    }

    private User createTemporaryUser(String username, String pw)
    {
        User user = new User();
        user.setUsername(username);
        user.setPw(DigestUtils.md5DigestAsHex(pw.getBytes()));
        return user;
    }

    private User onDuplicateError()
    {
        User user = new User();
        user.setUid(0);
        return user;
    }

}
