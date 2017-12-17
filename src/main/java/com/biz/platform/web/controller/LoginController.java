package com.biz.platform.web.controller;

import com.biz.platform.web.pojo.User;
import com.biz.platform.web.service.UserService;
import com.biz.platform.web.utils.AjaxResult;
import com.biz.platform.web.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by huangdonghua on 2017/12/14.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test.do")
    public String test(){
        return "index";
    }

    //登录
    @RequestMapping("/login.do")
    @ResponseBody
    public AjaxResult login(@RequestBody User user, HttpServletRequest request){

        if(user != null && StringUtils.isNullOrBlank(user.getUserCode())){
            return new AjaxResult(AjaxResult.STATUS_ERROR,"请输入账号");
        }

        User loginUser = userService.getUserCode(user);
        if(loginUser == null){
            return new AjaxResult(AjaxResult.STATUS_ERROR,"输入的账号或密码错误！");
        }
        request.getSession().setAttribute("loginUser",loginUser);

        return new AjaxResult(AjaxResult.STATUS_SUCCESS,loginUser);

    }

    //用户退出
    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:login.jsp";
    }


    /**
     * 前端验证码
     *
     * @param request
     * @param response
     * @param session  存放验证
     * @throws IOException
     */
    @RequestMapping({"authCode"})
    public void getAuthCode(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        int width = 63;
        int height = 37;
        Random random = new Random();
        // 设置response头信息
        // 禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        // 产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        // Graphics类的样式
        g.setColor(this.getRandColor(200, 250));
        g.setFont(new Font("Times New Roman", 0, 28));
        g.fillRect(0, 0, width, height);
        // 绘制干扰线
        for (int i = 0; i < 40; i++) {
            g.setColor(this.getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        // 绘制字符
        String strCode = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            strCode = strCode + rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 28);
        }
        // 将字符保存到session中用于前端的验证
        session.setAttribute("strCode", strCode);
        g.dispose();

        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();

    }

    //创建颜色
    public Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
