package com.excel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excel.service.UserInfoService;

@Controller
public class HelloController {
	@Autowired
    private UserInfoService userInfoService;
	
    @RequestMapping("/index")
    public String index(){
        return "manage";
    }
    
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public String savePerson(){
    	userInfoService.saveUserInfo();
        return "success!";
    }
}