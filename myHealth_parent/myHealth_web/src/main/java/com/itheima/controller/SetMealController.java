package com.itheima.controller;
import	java.io.FileInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.Constant.MessageConstant;
import com.itheima.Constant.RedisConstant;
import com.itheima.mapping.PageResult;
import com.itheima.mapping.QueryPageBean;
import com.itheima.mapping.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetMealService;
import com.itheima.utils.QiniuUtils;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 19:41 2019/11/3
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private SetMealService setMealService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        try {
            String originalFilename = imgFile.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String piffex = UUID.randomUUID().toString();
            String fileName = piffex + suffix;
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        try {
            setMealService.add(setmeal, checkgroupIds);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    //新增
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            PageResult pageResult = setMealService.findPage(queryPageBean);
            return pageResult;
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Setmeal setmeal = setMealService.findById(id);
            return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS, setmeal);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
    }

    @RequestMapping(value = "/exportBusinessReport",method = RequestMethod.GET)
    public Result exportBusinessReport(HttpServletRequest request, HttpServletResponse response) {
        try {
            //1.获取运营数据统计报表数据
            Map<String, Object> businessReportData = setMealService.getBusinessReportData();
            //2.获取模板路径  File.separator 根据不同的系统返回相应目录符号
            String filePath = request.getSession().getServletContext().getRealPath("report") + File.separator + "report_template.xlsx";
            //3.获取Excel模板对象 XSSFWorkbook：2007  HSSFWorkbook：2003
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            //通过第三方模板技术  excel模板对象（例子：改造${reportData}） 模板对应数据map
            //针对poi开发模板技术  jxl
            XLSTransformer transformer = new XLSTransformer();
            transformer.transformWorkbook(xssfWorkbook, businessReportData);
            //5.通过输出流返回页面下载本地磁盘
            OutputStream outputStream = response.getOutputStream();
            //设置文件名 文件类型
            //文件类型 告知浏览器返回2007 excel
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            //attachment:以附件的形式下载 filename=report.xlsx:指定下载文件名
            response.setHeader("content-Disposition","attachment;filename=report.xlsx");
            xssfWorkbook.write(outputStream);
            //6.释放资源
            outputStream.flush();
            outputStream.close();
            xssfWorkbook.close();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
        return null;
    }

    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData() {
        try {
            Map map = setMealService.getBusinessReportData();
            return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS, map);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
    }
}
