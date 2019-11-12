package com.itheima.service;

import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 16:04 2019/10/30
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface CheckItemService {
    void addCheckItem(CheckItem checkItem);
    PageResult findPage(Integer pageSize, Integer currentPage, String queryString);
    CheckItem findById(int id);
    void delete(int id);
    void edit(CheckItem checkItem);
    List<CheckItem> findAll();
    List<Integer> findCheckItemIdsByCheckGroupId(Integer checkgroup_id);
}

