package com.itheima.health.service.Impl;
import	java.util.HashMap;
import java.util.List;
import	java.util.Map;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 23:20 2019/11/2
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;
    @Override
    public void addCheckItem(CheckGroup checkGroup, Integer[] checkgroupIds) {
            checkGroupDao.addCheckGroup(checkGroup);
        CheckGroupCheckItem(checkGroup.getId(),checkgroupIds);
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckGroup> checkGroupPage=checkGroupDao.findPage(queryPageBean.getQueryString());
        PageResult pageResult=new PageResult(checkGroupPage.getTotal(),checkGroupPage.getResult());
        return pageResult;
    }

    @Override
    public void edit(Integer[] checkgroupIds, CheckGroup checkGroup) {
        checkGroupDao.updateCheckGroup(checkGroup);
        checkGroupDao.delete(checkGroup.getId());
        CheckGroupCheckItem(checkGroup.getId(),checkgroupIds);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    public void CheckGroupCheckItem(Integer checkgroup_id, Integer[] checkitemIds) {
        if (checkitemIds!=null&&checkitemIds.length>0){
            for (Integer checkitem_id : checkitemIds) {
                Map<String,Integer>map=new HashMap<>();
                map.put("checkgroup_id",checkgroup_id);
                map.put("checkitem_id",checkitem_id);
                checkGroupDao.addCheckGroupCheckItem(map);
            }
        }
    }
}
