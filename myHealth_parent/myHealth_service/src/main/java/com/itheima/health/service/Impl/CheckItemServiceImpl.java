package com.itheima.health.service.Impl;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.Constant.MessageConstant;
import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.dao.CheckItemDao;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 16:04 2019/10/30
 * @ Description：
 * @ Modified By：
 * @Version: $
 */

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;
    //新增
    @Override
    public void addCheckItem(CheckItem checkItem) {
        checkItemDao.addCheckItem(checkItem);
    }

    @Override
    public PageResult findPage(Integer pageSize, Integer currentPage, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page=checkItemDao.selectIf(queryString);
        PageResult result = new PageResult(page.getTotal(),page.getResult());
        return result;
    }

    @Override
    public CheckItem findById(int id) {
        return checkItemDao.findById(id);
    }

    @Override
    public void delete(int id) {
        int count=checkItemDao.selectIfAssociate(id);
        if (count>0){
            throw new RuntimeException(MessageConstant.DELETE_CHECKITEM_SUCCESSIFCOVERMAKE);
        }
        checkItemDao.delete(id);
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer checkgroup_id) {
        return checkItemDao.findCheckItemIdsByCheckGroupId(checkgroup_id);
    }
}