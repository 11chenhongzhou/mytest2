package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Constant.MessageConstant;
import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.mapping.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 16:02 2019/10/30
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;

    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.addCheckItem(checkItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    //分页
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            PageResult pageResult = checkItemService.findPage(queryPageBean.getPageSize(), queryPageBean.getCurrentPage(), queryPageBean.getQueryString());
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //回想
    @RequestMapping("/findById")
    public Result findById(int id) {
        Result result = new Result(true);
        try {
            CheckItem checkItem = checkItemService.findById(id);
            result.setData(checkItem);
        } catch (Exception e) {
            result.setFlag(false);
        }
        return result;
    }

    //删除
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result delete(int id) {
        Result result = new Result(true);
        try {
            checkItemService.delete(id);
            result.setMessage(MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (RuntimeException e) {
            result.setFlag(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setFlag(false);
            result.setMessage(MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return result;
    }

    //新增
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.edit(checkItem);
            return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<CheckItem> checkItemList=checkItemService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemList);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer checkgroup_id) {
        try {
            List<Integer> list=checkItemService.findCheckItemIdsByCheckGroupId(checkgroup_id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}

