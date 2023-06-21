package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.constants.Constants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.HSSFUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.domain.ActivitiesRemark;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.mapper.ActivitiesRemarkMapper;
import com.bjpowernode.crm.workbench.service.ActivityRemarkService;
import com.bjpowernode.crm.workbench.service.ActivityService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-05-27  15:40
 * @Description TODO
 * @since 1.0
 */
@Controller
public class ActivityController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request) {
        //调用service层方法，查询所有用户
        List<User> userList = userService.queryAllUsers();
        //把数据保存到request中
        request.setAttribute("userList", userList);
        //请求转发到市场活动的主页面
        return "workbench/activity/index";
    }

    //                 加/是代替前面的地址端口号项目名        和方法名保存一致
    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    //@ResponseBody自动生成json              客户提交的参数直接在接收时自动都封装好在activity
    public @ResponseBody Object saveCreateActivity(Activity activity, HttpSession session) {
        //封装自己生成的3个参数
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formatDateTime(new Date()));
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        activity.setCreateBy(user.getId());
        //调用service层,保存创建的市场活动
        ReturnObject returnObject = new ReturnObject();
        try {
            //service
            int ret = activityService.saveCreateActivity(activity);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试");
            }
        } catch (Exception e) {
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试");
//            throw new RuntimeException(e);
            e.printStackTrace();
        }

        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    public @ResponseBody Object queryActivityByConditionForPage(String name, String owner, String startDate, String endDate,
                                                                int pageNo, int pageSize) {
        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        //调用service层方法，查询数据
        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int totalRows = activityService.queryCountOfActivityByCondition(map);
        //根据查询结果，生成响应信息
        HashMap<String, Object> retMap = new HashMap<>();
        retMap.put("activityList", activityList);
        retMap.put("totalRows", totalRows);
        return retMap;
    }

    @RequestMapping("/workbench/activity/deleteActivityByIds.do")
    public @ResponseBody Object deleteActivityByIds(String[] id) {
        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法，删除市场活动
            int ret = activityService.deleteActivityByIds(id);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试...");
            }
        } catch (Exception e) {
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试...");
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityById.do")
    public @ResponseBody Object queryActivityById(String id) {
        //调用service方法，查询市场活动
        Activity activity = activityService.queryActivityById(id);
        //根据查询结果，返回响应信息
        return activity;
    }

    @RequestMapping("/workbench/activity/saveEditActivity.do")
    public @ResponseBody Object saveEditActivity(Activity activity, HttpSession session) {
        //封装自己生成的2个参数
        activity.setEditTime(DateUtils.formatDateTime(new Date()));
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        activity.setEditBy(user.getId());
        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层,保存修改的市场活动
            int ret = activityService.saveEditActivity(activity);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试");
            }
        } catch (Exception e) {
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试");
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/fileDownload.do")
    public void fileDownload(HttpServletResponse response) throws Exception {
        //response
        //1.设置响应类型          application/octet-stream
        // application/vnd.ms-excel
        // application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
        response.setContentType("application/octet-stream;charset=UTF-8");
        //2.获取输出流
        OutputStream out = response.getOutputStream();
        //浏览器默认行为：浏览器接收到响应信息之后，默认情况下，直接在显示窗口中打开响应信息，即使打不开，也会调用应用程序打开，
        //只有实在打不开，才会激活文件下载窗口，让你另存为。
        //可以设置响应头信息，使浏览器接收到响应信息之后，直接激活文件下载窗口，即使能打开也不打开
        response.addHeader("Content-Disposition", "attachment;filename=myStudentList.xls");
        //3.读取磁盘中excel文件，输出到浏览器
        InputStream is = new FileInputStream("E:\\程序设计\\CRM\\serverDir\\studentList.xls");
        byte[] buff = new byte[256];
        int len = 0;
        while ((len = is.read(buff)) != -1) {
            out.write(buff, 0, len);
        }
        //关闭资源，谁new的谁关，out是tomcat new的 你刷新一下就传走了
        is.close();
        out.flush();
        System.out.println("=================");
    }

    @RequestMapping("/workbench/activity/exportAllActivities.do")
    public void exportAllActivities(HttpServletResponse response) throws IOException {
        //调用service层方法，查询所有市场活动
        List<Activity> activities = activityService.queryAllActivities();
        //创建excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");
        //判断是否有元素，没元素就不浪费时间了
        if (activities != null && activities.size() > 0) {
            //遍历activityList，创建HSSFRow对象
            Activity activity = null;
            for (int i = 0; i < activities.size(); i++) {
                activity = activities.get(i);
                //每遍历出一个activity，生成一行
                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }

        //根据workbook生成excel文件
        /*
        OutputStream fileOutputStream = new FileOutputStream("E:\\程序设计\\CRM\\serverDir\\activityList.xls");
        workbook.write(fileOutputStream);
        //关闭资源
        fileOutputStream.close();
        workbook.close();
        */
        //把生成的excel下载到客户端

        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=activityList.xls");
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        /*
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\程序设计\\CRM\\serverDir\\activityList.xls"));
        byte[] buff = new byte[1024];
        int readLen = 0;
        while ((readLen = bis.read(buff)) != -1) {
            bos.write(buff, 0, readLen);
        }
        bis.close();
        */
        workbook.write(bos);

        workbook.close();
        bos.close();

    }

    @RequestMapping("workbench/activity/exportActivities.do")
    public void exportActivities(@RequestParam("ids") String id, HttpServletResponse response) throws IOException {
        String idStr = "&" + id;
        String[] ids = idStr.split("&id=");

        //调用service层方法，查询市场活动
        List<Activity> activities = activityService.queryActivityByIds(ids);
//        System.out.println(activities);
        //创建excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");
        //判断是否有元素，没元素就不浪费时间了
        if (activities != null && activities.size() > 0) {
            //遍历activityList，创建HSSFRow对象
            Activity activity = null;
            for (int i = 0; i < activities.size(); i++) {
                activity = activities.get(i);
                //每遍历出一个activity，生成一行
                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
            //把生成的excel下载到客户端

            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=activityPartList.xls");
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            workbook.write(bos);
            //
//            OutputStream fileOutputStream = new FileOutputStream("E:\\程序设计\\CRM\\serverDir\\activityTestList.xls");
//            workbook.write(fileOutputStream);
            //
            //关闭资源
            workbook.close();
            bos.close();
        }
    }

    @RequestMapping("/workbench/activity/fileUpload.do")
    public @ResponseBody Object fileUpload(String userName, MultipartFile myFile) throws IOException {
        //把文本数据打印到控制台
        System.out.println("userName = " + userName);
        String originalFilename = myFile.getOriginalFilename();

        //把文件在服务器指定的目录中生成同样的文件       +UUID解决文件在服务器文件保存目录重名问题
        File file = new File("E:\\程序设计\\CRM\\serverDir\\", UUIDUtils.getUUID() + originalFilename);
        myFile.transferTo(file);

        //返回响应信息
        ReturnObject returnObject = new ReturnObject();
        returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
        returnObject.setMessage("上传成功");
        return returnObject;
    }

    @RequestMapping("/workbench/activity/importActivity.do")
    public @ResponseBody Object importActivity(MultipartFile activityFile, String userName, HttpSession session) {
        System.out.println("userName" + userName);
        ReturnObject returnObject = new ReturnObject();
        try {
            /*
            //把接收到的excel文件写到磁盘目录中
            activityFile.transferTo(
                    new File("E:\\程序设计\\CRM\\serverDir\\", activityFile.getName()));
            //根据指定的excel文件生成HSSFWorkbook对象，封装了excel文件的所有信息
            FileInputStream is = new FileInputStream("E:\\程序设计\\CRM\\serverDir\\" + activityFile.getName());
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            */
            //multipartFile自带获取流
            InputStream inputStream = activityFile.getInputStream();
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheetAt = workbook.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            Activity activity = null;
            String cellValue = null;
            ArrayList<Activity> activityList = new ArrayList<>();
            //第一行标题行不用解析
            System.out.println(sheetAt.getLastRowNum());
            for (int i = 1; i <= sheetAt.getLastRowNum(); i++) {
                row = sheetAt.getRow(i);
                activity = new Activity();
                //id自动生成
                activity.setId(UUIDUtils.getUUID());
                //导入者为所有者
                User user = (User) session.getAttribute(Constants.SESSION_USER);
                activity.setOwner(user.getId());
                //导入时间为创建时间
                activity.setCreateTime(DateUtils.formatDateTime(new Date()));
                //导入者为所有者
                activity.setCreateBy(user.getId());
                //修改人和时间不上传  ，上传有问题InvocationTargetException
//                activity.setEditTime("2023-06-06 21:04:05");
//                activity.setEditBy("045abca548d644a9b4e0951b94656469");
                System.out.println(row.getLastCellNum());
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    cellValue = HSSFUtils.getCellValueForStr(cell);
                    if (j == 0) {
                        activity.setName(cellValue);
                    } else if (j == 1) {
                        activity.setStartDate(cellValue);
                    } else if (j == 2) {
                        activity.setEndDate(cellValue);
                    } else if (j == 3) {
                        activity.setCost(cellValue);
                    } else if (j == 4) {
                        activity.setDescription(cellValue);
                    }
                }
                //将activity保存到集合中
                activityList.add(activity);
            }
            //调用service层方法
            int count = activityService.saveCreateActivityByList(activityList);

            returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(count);
        } catch (IOException e) {
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后重试");
            e.printStackTrace();
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/detailActivity.do")
    public String detailActivity(String id, HttpServletRequest request) {
        //调用service层查询数据
        Activity activity = activityService.queryActivityForDetailById(id);
        List<ActivitiesRemark> remarkList = activityRemarkService.queryActivityRemarkForDetailByActivityId(id);
        //把数据保存到request
        request.setAttribute("activity",activity);
        request.setAttribute("remarkList",remarkList);
        //请求转发
        return  "workbench/activity/detail";
    }
}
