package ru.workbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ReadExel {
    public static final String path = "E:\\DevTransfer\\reload\\test_spel\\src\\main\\resources\\";
    public static void main(String[] args) throws IOException {


        public static void readFromExcel(String file) {

            HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(path + "Запуск по главам - А.xlsx"));
            HSSFSheet myExcelSheet = myExcelBook.getSheet("Сверка");
            HSSFRow row = myExcelSheet.getRow(0);

            if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
                String name = row.getCell(0).getStringCellValue();
                System.out.println("name : " + name);
            }
            
            myExcelBook.close();

        }




    }
}
