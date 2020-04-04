package edu.bistu.cstp.service;

import edu.bistu.cstp.dao.entity.User;

public interface UserService
{
    /*用户名密码是否匹配*/
    User isLoginInfoValid(String username, String pw);

    /*创建新用户*/
    User createNewUser(String username, String pw);
}
