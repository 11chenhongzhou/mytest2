package com.itheima.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 18:04 2019/11/7
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Override
    public Member selectIfMember(String telephone) {
        return memberDao.selectIfMember(telephone);
    }
    @Override
    public void add(Member member) {
        memberDao.add(member);
    }
}
