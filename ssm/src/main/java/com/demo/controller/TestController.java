package com.demo.controller;

import com.demo.domain.Account;
import com.demo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class TestController {

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Object query(){
        List list = accountService.query();
        logger.debug("log debug打印日志");
        logger.info("log info打印日志");
        logger.error("log error打印日志");
        return list;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object queryList(){
        List list = accountService.queryList();
        return list;
    }

    //测试中文国际化
    @RequestMapping(value = "/testI18n", method = RequestMethod.POST)
    @ResponseBody
    public Object testI18n(HttpServletRequest request, HttpServletResponse response){
        RequestContext req = new RequestContext(request);
        String driverClasss = req.getMessage("driverClasss");
        String unKnow = req.getMessage("unKnow");
        return driverClasss + unKnow;
    }

    @RequestMapping(value = "/{userid}/query", method = RequestMethod.POST)
    @ResponseBody
    public String query(@PathVariable(value = "userid") String userid, @Valid Account account, BindingResult result){
        if(result.hasErrors()){
            List errList = result.getAllErrors();
            return "error";
        }else {
            return account.getNum().toString();
        }
    }

}
