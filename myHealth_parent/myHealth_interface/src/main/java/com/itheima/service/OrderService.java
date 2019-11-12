package com.itheima.service;

import com.itheima.mapping.Result;
import com.itheima.pojo.Order;

import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 15:21 2019/11/6
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface OrderService {
    Result submit(Map map) throws Exception;

    Result findById(Integer id) throws Exception;
}
