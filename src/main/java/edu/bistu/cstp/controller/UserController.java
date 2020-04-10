package edu.bistu.cstp.controller;

import edu.bistu.cstp.dao.entity.User;
import edu.bistu.cstp.domain.login.LoginRequest;
import edu.bistu.cstp.domain.register.RegisterRequest;
import edu.bistu.cstp.domain.register.RegisterResult;
import edu.bistu.cstp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController
{
    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/data/register")
    public RegisterResult register(@RequestBody RegisterRequest registerRequest, HttpSession httpSession)
    {
        User result = userService.createNewUser(registerRequest.getUsername(), registerRequest.getPw());
        if(result == null)
        {
            /*发生意外*/
            return RegisterResult.ERROR;
        }
        else if(result.getUid() == 0)
        {
            /*用户名重复*/
            return RegisterResult.DUPLICATE;
        }
        else
        {
            /*成功*/
            httpSession.setAttribute("id", result.getUsername());
            return RegisterResult.SUCCESS;
        }
    }

    @GetMapping("/data/identity")
    public String identity(HttpSession httpSession)
    {
        /*验证session*/
        String username = (String) httpSession.getAttribute("id");
        if(username == null)
            return "";
        else
            return username;
    }

    @PostMapping("/data/logout")
    public void logout(HttpSession httpSession)
    {
        //System.out.println("method logout triggered");
        httpSession.removeAttribute("id");
    }

    @PostMapping("/data/login")
    public boolean login(@RequestBody LoginRequest loginRequest, HttpSession httpSession)
    {
        System.out.println("method login triggered: " + loginRequest.getUsername() + " " + loginRequest.getPw());
        User res = userService.isLoginInfoValid(loginRequest.getUsername(), loginRequest.getPw());
        if(res != null && res.getUid() > 0)
        {
            httpSession.setAttribute("id", res.getUsername());
            return true;
        }
        else
            return false;
    }
}
