package edu.bistu.cstp.controller;

import edu.bistu.cstp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController
{
    private UserService userService;

    @Autowired
    public PageController(UserService userService)
    {
        this.userService = userService;
    }

    @RequestMapping("/page/login")
    public String userLogin()
    {
        return "login";
    }

    @RequestMapping("/page/register")
    public String register()
    {
        return "register";
    }

    @RequestMapping("/page/home_page")
    public String homePage()
    {
        return "home_page";
    }

    @RequestMapping("/page/user_center")
    public String userCenter()
    {
        return "user_center";
    }

    @RequestMapping("/page/goods")
    public String goods()
    {
        return "goods";
    }

    @RequestMapping("/")
    public String defaultPage()
    {
        return homePage();
    }

}
