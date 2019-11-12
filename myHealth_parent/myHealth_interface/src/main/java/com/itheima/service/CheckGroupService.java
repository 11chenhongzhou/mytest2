package com.itheima.service;

import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 23:19 2019/11/2
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface CheckGroupService {
    void addCheckItem(CheckGroup checkGroup, Integer[] checkitemIds);
    CheckGroup findById(Integer id);
    PageResult findPage(QueryPageBean queryPageBean);
    void edit(Integer[] checkitemIds, CheckGroup checkGroup);
    List<CheckGroup> findAll();
}
