package com.itheima.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.ReportDao;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 19:20 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service(interfaceClass =ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportDao reportDao;
    @Override
    public Integer getMemberReport(String months) {
        return reportDao.getMemberReport(months);
    }

    @Override
    public  List<Map> getSetmealReport() {
        return reportDao.getSetmealReport();
    }
}
