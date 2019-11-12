package com.itheima.service;

import com.itheima.mapping.Result;

import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 17:38 2019/11/7
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface LoginService {
    Result check(Map map) throws Exception;
}
