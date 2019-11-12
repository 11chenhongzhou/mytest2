package com.itheima.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.LoginDao;
import com.itheima.dao.MemberDao;
import com.itheima.mapping.Result;
import com.itheima.pojo.Member;
import com.itheima.service.LoginService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 17:37 2019/11/7
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service(interfaceClass = LoginService.class)
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;
    @Override
    public Result check(Map map) throws Exception {
        return null;
    }
}
