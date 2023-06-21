package com.bjpowernode.crm.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;

/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-06-07  23:19
 * @Description TODO 使用apache-poi生成excel文件
 * @since 1.0
 */
public class CreateExcelTest {
    public static void main(String[] args) throws Exception {
        //创建HSSFWorkBook对象，对应一个excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //使用wb创建HSSFSheet，对应wb文件的一个sheet
        HSSFSheet sheet = wb.createSheet("学术列表");
        //使用sheet创建HSSFRow对象，对应sheet中的一行
        HSSFRow row = sheet.createRow(0);//行号 0开始
        //使用row创建HSSFCell对象
        HSSFCell cell = row.createCell(0);//列的编号：从0开始，依次增加
        cell.setCellValue("学号");
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell = row.createCell(2);
        cell.setCellValue("年龄");

        //生成HSSFCellStyle对象
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//设置居中

        //使用sheet创建10个HSSFRow对象，对应sheet中10行
        for (int i = 1; i <= 10; i++) {
            row = sheet.createRow(i);

            cell = row.createCell(0);//列的编号：从0开始，依次增加
            cell.setCellValue(100 + i);

            cell = row.createCell(1);
            cell.setCellValue(i + "name");

            cell = row.createCell(2);
            cell.setCellStyle(style);//居中
            cell.setCellValue(20 + i);
        }
        //调用工具函数生成excel文件  //目录不能自动创建，文件能自动创建
        OutputStream os = new FileOutputStream("E:\\程序设计\\CRM\\serverDir\\studentList.xls");
        wb.write(os);
        //关闭资源
        os.close();
        wb.close();
        System.out.println("======create xls ok=====");
    }
}
