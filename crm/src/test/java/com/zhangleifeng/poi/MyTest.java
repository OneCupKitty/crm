package com.zhangleifeng.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @program: crm-project
 * @description:
 * @author: Zhang_Leifeng
 * @create: 2022-08-04 14:45
 **/
public class MyTest {
    @Test
    public void testPoi(){
        //创建HSSFWorkbook对象，对应一个excel文件
        HSSFWorkbook wb=new HSSFWorkbook();
        //使用wb创建HSSFSheet对象，对应wb文件中的一页
        HSSFSheet sheet=wb.createSheet("学生列表");
        //使用sheet创建HSSFRow对象，对应sheet中的一行
        HSSFRow row=sheet.createRow(0);//行号：从0开始,依次增加
        //使用row创建HSSFCell对象，对应row中的列
        HSSFCell cell=row.createCell(0);//列的编号：从0开始，依次增加
        cell.setCellValue("学号");
        cell=row.createCell(1);
        cell.setCellValue("姓名");
        cell=row.createCell(2);
        cell.setCellValue("年龄");

        //生成HSSFCellStyle对象
        HSSFCellStyle style=wb.createCellStyle();

        //使用sheet创建10个HSSFRow对象，对应sheet中的10行
        for(int i=1;i<=10;i++){
            row=sheet.createRow(i);

            cell=row.createCell(0);//列的编号：从0开始，依次增加
            cell.setCellValue(100+i);
            cell=row.createCell(1);
            cell.setCellValue("NAME"+i);
            cell=row.createCell(2);
            cell.setCellStyle(style);
            cell.setCellValue(20+i);
        }

        //调用工具函数生成excel文件
        try {
            //目录必须手动创建，文件如果不存在，poi会自动创建"D:\User\Desktop"
            OutputStream os=new FileOutputStream("D:\\User\\Desktop\\userList.xls");
            wb.write(os);

            //关闭资源
            os.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===========create ok==========");
    }
}
