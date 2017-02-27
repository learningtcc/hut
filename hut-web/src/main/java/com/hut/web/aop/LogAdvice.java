package com.hut.web.aop;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Jared on 2016/12/23.
 */
@Component
@Aspect
public class LogAdvice {


    public static void main(String[] args) throws IOException {
        //得到Excel文件对象
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("C:/Users/ok/Desktop/index.xlsx"));
        //得到Excel工作表
        XSSFSheet sheet = wb.getSheetAt(0);

        //得到Excel工作表指定行的单元格
        for(int i= 1;i<11;i++){
            XSSFRow row = sheet.getRow(i);
            for(int j=10;j<20;j++){
                XSSFCell cell = row.getCell(j);
                cell.setCellValue(16.56);
                }
            }

    }

    @Pointcut("execution(com.hut.web.controller.*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(){
        System.out.println("方法执行前");
    }

    @After("pointcut()")
    public void after(){
        System.out.println("方法执行后");
    }

}
