package com.twomeng.modules.sys.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 1. 获取验证码
 * 2. 用户点击登录按钮进行登录，根据sys_user用户名和密码验证登录权限，shiro
 * 3. 登录退出功能
 */
@Api(tags = "用户登录")
@Controller
@RequestMapping("/sys/")
public class SysLoginController {

    /**
     * 1. 引入依赖
     * 2. 引入配置文件
     */
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/kaptcha.jpg")
    public void getKaptcha(HttpServletResponse response) throws IOException {
        /*
        1. request和response是服务器创建的，request用于封装用户请求的数据，response用于封装响应数据
        2. setContentType 设置MIME类型：
        服务器使用文档后缀来区分不同文件的MIME类型，比如html, jpg等，客户端只是从服务器接收数据流而不知道文档的后缀名称，因此服务器必须使用
        附加信息即MIME告诉客户端程序，客户端程序才会根据不同的MIME调用浏览器内不同的程序嵌入模块来处理
        3. response的常用响应头设置缓存为没有缓存，防止验证码无法刷新
        4. ImageIo是Java进行读写图片的一个工具，write将图片按照jpg的格式写入到response的输出流中
         */
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control","no-cache");
        // 创建Img,BufferedImage，一个带缓冲区的图像类
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        // 通过shiro将验证码进行存储
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("kaptcha", text);
        // 这里传输图片应该用字符流而不是字节流
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
    }

    /*
    服务器端收到客户端发来的用户名，密码，验证码
     */
    @PostMapping("/login")
    public ModelAndView userLogin(String username, String password, String captcha){
        ModelAndView modelAndView = new ModelAndView();
        // 获取当前执行用户
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        if (!captcha.equals(session.getAttribute("kaptcha"))){
            modelAndView.addObject("msg", "验证码错误");
            return modelAndView;
        }

        if ( !subject.isAuthenticated() ) { // 如果当前用户还未被授权
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //this is all you have to do to support 'remember me' (no config - built in!):
            token.setRememberMe(true);
            subject.login(token);
        }
        return modelAndView;
    }
}
