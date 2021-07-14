package com.nghbui.main;


import com.nghbui.model.ShortShift;
import com.nghbui.model.Task;
import com.nghbui.model.Work;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ReadWrite {
    public static List<Task> tasks = new ArrayList<Task>();
    public static List<ShortShift> shortShifts = new ArrayList<>();

    public void ReadData() {
        File file = new File("E:\\InformationTechnology\\Internship\\AssignmentSupermarket_2\\src\\main\\java\\com\\nghbui\\data\\TemplateImport_CalAssignmentData_Fresher.xlsx");
        try {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Task task = new Task();
                task.setDate(row.getCell(0).getDateCellValue());
                task.setSupermarketID((int) row.getCell(1).getNumericCellValue());
                task.setLongShiftID((int) row.getCell(2).getNumericCellValue());
                task.setShortShiftID((int) row.getCell(3).getNumericCellValue());
                task.setHeadCountInShift((int) row.getCell(5).getNumericCellValue());
                task.setShortShiftTime((int) row.getCell(6).getNumericCellValue());
                task.setLongShiftTime((int) row.getCell(7).getNumericCellValue());
                task.setNameWork(row.getCell(8).getStringCellValue());
                task.setCategoryWork((int) row.getCell(9).getNumericCellValue());
                task.setQuantityHumansWorking((int) row.getCell(11).getNumericCellValue());
                task.setMinutesFinishWork((int) row.getCell(12).getNumericCellValue());
                tasks.add(task);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        if (tasks!=null) {
            for(Task task: tasks) {
                ShortShift shortShift = new ShortShift();
                shortShift.setDate(task.getDate());
                shortShift.setSupermarketID(task.getSupermarketID());
                shortShift.setLongShiftID(task.getLongShiftID());
                shortShift.setLongShiftTime(task.getLongShiftTime());
                shortShift.setShortShiftID(task.getShortShiftID());
                shortShift.setShortShiftTime(task.getShortShiftTime());
//                shortShift.setWorks();
                shortShift.setHeadCount(task.getHeadCountInShift());

                boolean flag = true;
                for (int i=0;i<shortShifts.size();i++) {
                    if (shortShifts.get(i).equals(shortShift)) {
                        flag =false;
                    }
                }
                if(flag)  {
                    shortShifts.add(shortShift);
                }
            }
        }
        //Add work into Short shift
        for (ShortShift shortShiftItem: shortShifts) {
            List<Work> works = shortShiftItem.getWorks();
            if (works == null) {
                works = new ArrayList<>();
            }
            for (Task task: tasks) {
                ShortShift shortShift = new ShortShift();
                shortShift.setDate(task.getDate());
                shortShift.setSupermarketID(task.getSupermarketID());
                shortShift.setLongShiftID(task.getLongShiftID());
                shortShift.setLongShiftTime(task.getLongShiftTime());
                shortShift.setShortShiftID(task.getShortShiftID());
                shortShift.setShortShiftTime(task.getShortShiftTime());
//                shortShift.setWorks();
                shortShift.setHeadCount(task.getHeadCountInShift());
                if (shortShift.equals(shortShiftItem)) {
                    Work work = new Work();
                    work.setWorkName(task.getNameWork());
                    work.setQuantityHumansWorking(task.getQuantityHumansWorking());
                    work.setCategoryWork(task.getCategoryWork());
                    work.setMinutesFinishWork(task.getMinutesFinishWork());
                    works.add(work);
                }
            }
            shortShiftItem.setWorks(works);
        }
    }
    public void WriteData(List<ShortShift> shortShifts) {
        //Write excel file
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");
            CreationHelper creationHelper = workbook.getCreationHelper();
            int currentRow =0;
            Row row = sheet.createRow(currentRow++);
            Cell cell = row.createCell(0);
            cell.setCellValue("Date");

            cell = row.createCell(1);
            cell.setCellValue("Supermarket ID");

            cell = row.createCell(2);
            cell.setCellValue("Long Shift ID");

            cell = row.createCell(3);
            cell.setCellValue("Short Shift ID");

            cell = row.createCell(4);
            cell.setCellValue("Short Shift Time");

            cell = row.createCell(5);
            cell.setCellValue("Work Name");

            cell = row.createCell(6);
            cell.setCellValue("Employee");


            for (ShortShift shortShift: shortShifts) {
                for(Work work: shortShift.getWorks()) {
                    row = sheet.createRow(currentRow++);
                    int currentCol = 0;
                    //Date
                    cell = row.createCell(currentCol++);
                    cell.setCellValue(new Date());
                    cell.setCellValue(shortShift.getDate());
                    CellStyle style = workbook.createCellStyle();
                    style.setDataFormat(creationHelper.createDataFormat().getFormat(
                            "MM-dd-yyyy"));
                    cell.setCellStyle(style);

                    //Supermarket ID
                    cell = row.createCell(currentCol++);
                    cell.setCellValue(shortShift.getSupermarketID());
                    //Long Shift
                    cell = row.createCell(currentCol++);
                    cell.setCellValue(shortShift.getLongShiftID());
                    //Short Shift
                    cell = row.createCell(currentCol++);
                    cell.setCellValue(shortShift.getShortShiftID());
                    //Short Shift Time
                    cell = row.createCell(currentCol++);
                    cell.setCellValue(shortShift.getShortShiftTime());

                    //Work name
                    cell = row.createCell(currentCol++);
                    cell.setCellValue(work.getWorkName());

                    //Employee

                    List<Integer> workers = work.getWorkers();
                    if (workers.size() !=0) {
                        String sWorker= "";
                        for (Integer iWorker : workers) {
                            sWorker +="NV"+iWorker+"  ";
                        }
                        cell = row.createCell(currentCol++);
                        cell.setCellValue(sWorker);
                    }else {
                        cell = row.createCell(currentCol++);
                        cell.setCellValue("NULL");
                    }
                }
            }
            FileOutputStream outputStream = new FileOutputStream("E:\\InformationTechnology\\Internship\\AssignmentSupermarket_2\\src\\main\\java\\com\\nghbui\\dataExport\\Output.xlsx");
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
