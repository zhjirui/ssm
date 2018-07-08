package com.demo.controller;

import com.demo.dao.entity.UserEntity;
import com.demo.domain.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public String queryList(){
        List list = userService.queryList();
        return "";
    }

    @RequestMapping(value = "/{userid}/query", method = RequestMethod.POST)
    @ResponseBody
    public String query(@PathVariable(value = "userid") String userid,  @Valid User user, BindingResult result){
        if(result.hasErrors()){
            List errList = result.getAllErrors();
            return "error";
        }else {
            UserEntity users = userService.getUserById(userid);
            return users.getUsername();
        }
    }
}