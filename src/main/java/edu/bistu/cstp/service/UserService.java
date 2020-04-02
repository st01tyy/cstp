package edu.bistu.cstp.service;

public interface UserService
{
    /*用户名密码是否匹配*/
    boolean isLoginInfoValid(String username, String pw);
}
