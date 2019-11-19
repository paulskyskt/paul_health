package com.paul.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.paul.constant.MessageConstant;
import com.paul.entity.Result;
import com.paul.pojo.MapData;
import com.paul.service.MemberService;
import com.paul.service.ReportService;
import com.paul.service.SetmealService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Reference
    private MemberService memberService;
    @Reference
    private SetmealService setmealService;

    @Reference
    private ReportService reportService;

    @RequestMapping("/getMemberReport.do")
    public Result getMemberReport() {
        Map<String, Object> map = new HashMap<>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -12);//现在时间往前12个月

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            calendar.add(Calendar.MONTH, 1); //日历锁定日期往后推一个月
            list.add(new SimpleDateFormat("yyyy.MM").format(calendar.getTime()));
        }
        map.put("months", list);

        List<Integer> memberCount = memberService.findMemberCountByMonth(list);
        map.put("memberCount", memberCount);


        return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, map);
    }


    @RequestMapping("/memberMap.do")
    public Result memberMap() {
        /*[
                        {name: '北京',value: 3},
                        {name: '天津',value:19},
                        {name: '上海',value:14},
                        {name: '重庆',value:1},
                        {name: '河北',value:2},
                        {name: '河南',value:5},
                        {name: '云南',value:10},
                        {name: '辽宁',value:9},
                        {name: '黑龙江',value:9},
                    ]*/
        //List<Map> list = memberService.findAddressAndCount();


        // ArrayList<MapData> list = new ArrayList<>();

    /*    MapData data = new MapData("北京", 3);
        MapData data1 = new MapData("重庆", 12);
        MapData data2 = new MapData("天津", 9);

        list.add(data);
        list.add(data1);
        list.add(data2);*/

        List<MapData> list = memberService.findAddressAndCount();

        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, list);

    }


    @RequestMapping("/getSetmealReport.do")
    public Result getSetmealReport() {
        /*
        {
            "setmealNames": ["套餐1", "套餐2", "套餐3"],
            "setmealCount": [
                  {"name": "套餐1","value": 10},
                  {"name": "套餐2","value": 30},
                  {"name": "套餐3","value": 25}
                ]
        }
        * */
        HashMap<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = setmealService.findSetmealCount();
        map.put("setmealCount", list);

        List<String> setmealNames = new ArrayList<>();

        for (Map<String, Object> objectMap : list) {
            String name = (String) objectMap.get("name");
            setmealNames.add(name);
        }

        map.put("setmealNames", setmealNames);


        return new Result(true, MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS, map);
    }

    @RequestMapping("/getBusinessReportData.do")
    public Result getBusinessReportData() {
        try {
            Map<String, Object> result = reportService.getBusinessReport();
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, result);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }

    @RequestMapping("/exportBusinessReport.do")
    public Result exportBusinessReport(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> result = reportService.getBusinessReport();

            //取出返回结果数据，准备将报表数据写入到Excel文件中
            String reportDate = (String) result.get("reportDate");
            Integer todayNewMember = (Integer) result.get("todayNewMember");
            Integer totalMember = (Integer) result.get("totalMember");
            Integer thisWeekNewMember = (Integer) result.get("thisWeekNewMember");
            Integer thisMonthNewMember = (Integer) result.get("thisMonthNewMember");
            Integer todayOrderNumber = (Integer) result.get("todayOrderNumber");
            Integer thisWeekOrderNumber = (Integer) result.get("thisWeekOrderNumber");
            Integer thisMonthOrderNumber = (Integer) result.get("thisMonthOrderNumber");
            Integer todayVisitsNumber = (Integer) result.get("todayVisitsNumber");
            Integer thisWeekVisitsNumber = (Integer) result.get("thisWeekVisitsNumber");
            Integer thisMonthVisitsNumber = (Integer) result.get("thisMonthVisitsNumber");
            List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");

            //获得Excel模板文件绝对路径
            String fileName = request.getSession().getServletContext().getRealPath("template")+ File.separator+"report_template.xlsx";
            //读取模板文件创建Excel表格对象
            XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File(fileName)));

            //读取第一个工作表
            XSSFSheet sheet = excel.getSheetAt(0);

            XSSFRow row = sheet.getRow(2);
            row.getCell(5).setCellValue(reportDate);//日期

            row = sheet.getRow(4);
            row.getCell(5).setCellValue(todayNewMember);//新增会员数（本日）
            row.getCell(7).setCellValue(totalMember);//总会员数

            row = sheet.getRow(5);
            row.getCell(5).setCellValue(thisWeekNewMember);//本周新增会员数
            row.getCell(7).setCellValue(thisMonthNewMember);//本月新增会员数

            row = sheet.getRow(7);
            row.getCell(5).setCellValue(todayOrderNumber);//今日预约数
            row.getCell(7).setCellValue(todayVisitsNumber);//今日到诊数

            row = sheet.getRow(8);
            row.getCell(5).setCellValue(thisWeekOrderNumber);//本周预约数
            row.getCell(7).setCellValue(thisWeekVisitsNumber);//本周到诊数

            row = sheet.getRow(9);
            row.getCell(5).setCellValue(thisMonthOrderNumber);//本月预约数
            row.getCell(7).setCellValue(thisMonthVisitsNumber);//本月到诊数

            int rowNum = 12;
            for(Map map : hotSetmeal){//热门套餐
                String name = (String) map.get("name");
                Long setmeal_count = (Long) map.get("setmeal_count");
                BigDecimal proportion = (BigDecimal) map.get("proportion");
                row = sheet.getRow(rowNum ++);
                row.getCell(4).setCellValue(name);//套餐名称
                row.getCell(5).setCellValue(setmeal_count);//预约数量
                row.getCell(6).setCellValue(proportion.doubleValue());//占比
            }

            //使用输出流进行表格下载,基于浏览器作为客户端下载
            OutputStream outputStream = response.getOutputStream();
            //代表的是Excel文件类型
            response.setContentType("application/vnd.ms-excel");
            //指定以附件形式进行下载
            response.setHeader("content-Disposition","attachment;filename=report.xlsx");

            excel.write(outputStream);

            outputStream.flush();
            outputStream.close();
            excel.close();

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }

    }

}
