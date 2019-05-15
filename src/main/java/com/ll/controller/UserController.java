package com.ll.controller;

import com.google.code.kaptcha.Producer;
import com.ll.entity.UserInfo;
import com.ll.exception.ReException;
import com.ll.service.UserService;
import com.ll.utils.ResultUtil;
import com.ll.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private Producer captchaProducer = null;

    @RequestMapping(value = "/findUserById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findUserById(@RequestParam("id") String id) {
        UserInfo userInfo = userService.getUserById(Integer.parseInt(id));
        log.info(userInfo.toString());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", userInfo);
        //System.out.println(userInfo.getUsername()+userInfo.getPassword()+userInfo.getAge()+userInfo.getId());
        return map;
    }

    @RequestMapping("/vcode")
    public void vcode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		VerifyCode vc = new VerifyCode();
//		BufferedImage image = vc.getImage();// 获取一次性验证码图片
        String text = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(text);
        // 该方法必须在getImage()方法之后来调用
        // System.out.println("验证码图片上的文本:"+vc.getText());//获取图片上的文本
        // 把文本保存到session中，为验证做准备
        //req.getSession().setAttribute("vcode", vc.getText());
        //保存到shiro session
//        ShiroUtils.setSessionAttribute("kaptcha", text);
        req.getSession().setAttribute("kaptcha", text);
        //VerifyCode.output(image, resp.getOutputStream());// 把图片写到指定流中
        ImageIO.write(image, "JPEG", resp.getOutputStream());
    }

    @RequestMapping(value = "/loginOut")
    public String loginOut() {
        ShiroUtils.logout();
        return "redirect:/login.jsp";
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResultUtil login(HttpServletRequest req, String username, String password, String vcode){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(vcode)) {
            throw new ReException("参数不能为空");
        }
        if (!vcode.toLowerCase().equals(((String) req.getSession().getAttribute("kaptcha")).toLowerCase())) {
            return ResultUtil.error("验证码不正确");
        }
        try {
            Subject subject = ShiroUtils.getSubject();
            //md5加密
            //password=DigestUtils.md5DigestAsHex(password.getBytes());
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ResultUtil.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResultUtil.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResultUtil.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResultUtil.error("用户验证失败");
        }
        return ResultUtil.ok();
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest req) {
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        req.setAttribute("userInfo", userInfo);
        return "redirect:/index.jsp";
    }
}
