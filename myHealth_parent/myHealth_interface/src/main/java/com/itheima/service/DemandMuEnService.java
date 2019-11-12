package com.itheima.service;

import com.itheima.pojo.Menu;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 15:41 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface DemandMuEnService {
    LinkedHashSet<Menu> findAllMuEn(String username);
}
