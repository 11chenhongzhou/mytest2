package com.itheima.controller;
import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Constant.MessageConstant;
import com.itheima.Constant.RedisMessageConstant;
import com.itheima.mapping.Result;
import com.itheima.pojo.Member;
import com.itheima.service.LoginService;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 17:36 2019/11/7
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Reference
    private LoginService loginService;
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private MemberService memberService;
    @RequestMapping("/check")
    public Result check(@RequestBody Map map, HttpServletResponse response, HttpServletRequest request) {
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");
        String code = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        if (validateCode == null || !code.equals(validateCode)) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Member member = memberService.selectIfMember(telephone);
        if (member==null){
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            memberService.add(member);
        }
        Cookie cookie=new Cookie("login_password",telephone);
        cookie.setPath("/");
        cookie.setMaxAge(50*600);
        response.addCookie(cookie);
        return new Result(true,MessageConstant.GET_USERNAME_SUCCESS);
    }
}
