package edu.bistu.cstp.controller;

import edu.bistu.cstp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController
{
    private UserService userService;

    @Autowired
    public HomePageController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/login")
    public void userLogin(@RequestParam String username, @RequestParam String password)
    {
        if(username == null || password == null)
            return;

    }

    @PostMapping("/register")
    public void register(@RequestParam String username, @RequestParam String password)
    {

    }

}
