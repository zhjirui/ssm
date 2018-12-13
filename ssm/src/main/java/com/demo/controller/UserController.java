package com.demo.controller;

import com.demo.dao.entity.UserEntity;
import com.demo.domain.Account;
import com.demo.domain.User;
import com.demo.service.AccountService;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object queryList(){
        List list = userService.queryList();
        logger.debug("log打印日志");
        logger.info("log打印日志");
        return list;
    }

    @RequestMapping(value = "/{userid}/list", method = RequestMethod.POST)
    @ResponseBody
    public Object queryObject(@PathVariable(value = "userid") String userid){
        Map map = userService.queryForObject(userid);
        return map;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String testTranstraction(User user) throws Exception{
        UserEntity userEntity = new UserEntity();
        Account account = new Account();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());

        account.setId(Integer.toString(user.getId()));
        account.setUserId(user.getUsername());
        account.setNum(user.getId());
        userService.createUser(userEntity);
        accountService.insert(account);
        return null;
    }
}
