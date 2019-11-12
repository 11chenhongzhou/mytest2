package com.itheima.service;

import com.itheima.pojo.Member;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 18:03 2019/11/7
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface MemberService {

    Member selectIfMember(String telephone);

    void add(Member member);
}
