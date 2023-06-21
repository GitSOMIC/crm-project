package com.bjpowernode.crm.poi;

import com.bjpowernode.crm.commons.utils.HSSFUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



/**
 * @author Jerry
 * @version 1.0
 * @CreateTime 2023-06-10  21:33
 * @Description TODO 使用apache-poi解析excel文件
 * @since 1.0
 */
public class ParseExcelTest {
    public static void main(String[] args) throws IOException {
        //根据指定的excel文件生成HSSFWorkbook对象，封装了excel文件的所有信息
        FileInputStream is = new FileInputStream("E:\\程序设计\\CRM\\serverDir\\studentList.xls");

        HSSFWorkbook workbook = new HSSFWorkbook(is);
        HSSFSheet sheetAt = workbook.getSheetAt(0);//页的下标 0开始
        HSSFRow row = null;
        HSSFCell cell = null;
        System.out.println(sheetAt.getLastRowNum());
        for (int i = 1; i <= sheetAt.getLastRowNum(); i++) {//lastRowNum最后一行下标
            row = sheetAt.getRow(i);//行的下标，从0开始，依次增加
            //根据row获取HSSFCell对象，封装了一列的所有信息
            //+1可能因为第一行是标题栏，不是数据行，所以行减一
            System.out.println(row.getLastCellNum());
            for (int j = 0; j < row.getLastCellNum(); j++) {//lastCellNum”最后一列的下标+1“
                cell = row.getCell(j);//列的下标，从0开始，依次增加
                if (j == 0 && i == 0) {
                    System.out.println(cell.getCellType());
                    System.out.println(CellType.STRING);
                    System.out.println("================");
                }
                //获取列中的数据
//                if (cell.getCellType() == CellType.STRING) {
//                    System.out.print(cell.getStringCellValue() + "  ");
//                } else if (cell.getCellType() == CellType.NUMERIC) {
//                    System.out.print(cell.getNumericCellValue() + "  ");
//                } else if (cell.getCellType() == CellType.BOOLEAN) {
//                    System.out.print(cell.getBooleanCellValue() + "  ");
//                } else if (cell.getCellType() == CellType.FORMULA) {
//                    System.out.print(cell.getCellFormula() + "  ");
//                } else if (cell.getCellType() == CellType.ERROR) {
//                    System.out.print(cell.getErrorCellValue() + "  ");
//                }
                String cellValueForStr = HSSFUtils.getCellValueForStr(cell);
                System.out.print(cellValueForStr+"  ");
            }
            //每一行所有列都打印完换行
            System.out.println();

        }
    }


}
