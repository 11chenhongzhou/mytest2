package com.itheima.service;

import java.util.List;
import java.util.Map;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 19:20 2019/11/11
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface ReportService {
    Integer getMemberReport(String months);

    List<Map> getSetmealReport();
}
