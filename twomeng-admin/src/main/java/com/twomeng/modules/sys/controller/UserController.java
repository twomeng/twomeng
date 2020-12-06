package com.twomeng.modules.sys.controller;


import com.twomeng.modules.sys.domain.SysUser;
import com.twomeng.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api
@Controller
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private SysUserService userService;

    /**
     * 查找全部的管理员
     * @return
     */
//    @ApiOperation(value = "展示所有管理员")
    @GetMapping("/list")
    public String list(@RequestParam("username") String name){
        System.out.println(name);
        List<SysUser> sysUserList = userService.list();
        System.out.println(sysUserList);
        return "login";
    }
}
