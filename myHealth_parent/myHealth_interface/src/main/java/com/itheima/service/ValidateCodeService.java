package com.itheima.service;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 15:42 2019/11/6
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface ValidateCodeService {
    void send4Order(String orderNotice, String s, String telephone);
}
