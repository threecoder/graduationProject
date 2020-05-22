package com.lutayy.campbackend.common.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {
    private static Logger logger = LogManager.getLogger(ExcelUtil.class);

    // 导出时统一时间输出格式
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    //统一数据输出格式
    private static DecimalFormat decimalFormat=new DecimalFormat("0");


    public static final int EXPORT_COUNT = 60000;

    /**
     * 数据导出为excel
     *
     * @param dataList
     *            List<Map<String, Object>>
     * @param mapNameList
     *            对列头对应的map中的key
     * @param titleList
     *            列头名
     * @param tempFileName
     *            eg: configParamValueReport.xls
     * @param sheetName
     * @param fileDir
     *            eg: uploaded/reportExport/
     * @return
     */


    /**
     * 读取xls文件，返回数据集
     *
     * @return Map[sheetName,List[Map[列头名,值]]]
     */
    public static Map<String, List<Map<String, String>>> readXls(
            InputStream inputStream) {
        // Map<sheetName,List<Map<列头名,值>>>
        Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(inputStream);
            if (wb != null) {
                int sheetsLength = wb.getNumberOfSheets();
                for (int k = 0; k < sheetsLength; k++) {
                    Sheet sheet = wb.getSheetAt(k);

                    int totalRowCount = sheet.getLastRowNum()
                            - sheet.getFirstRowNum() + 1;// 总行数
                    int totalColCount = 0;
                    if (sheet.getRow(0) != null) {
                        Row row = sheet.getRow(0);
                        totalColCount = row.getLastCellNum()
                                - row.getFirstCellNum();
                    }

                    List<Map<String, String>> list = new ArrayList<Map<String, String>>(
                            sheetsLength);
                    String[] keys = new String[totalColCount];

                    // 第一行列头做为key
                    for (int i = 0; i < totalColCount; i++) {
                        Row row = sheet.getRow(0);
                        Cell cell = row.getCell(i);

                        int cellType = cell.getCellType();
                        String stringCellValue = "";
                        switch (cellType) {
                            case Cell.CELL_TYPE_BOOLEAN:
                                stringCellValue = "" + cell.getBooleanCellValue();
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                stringCellValue = "" + decimalFormat.format(cell.getNumericCellValue());
                                break;
                            case Cell.CELL_TYPE_STRING:
                                stringCellValue = "" + cell.getStringCellValue();
                                break;
                        }
                        if (stringCellValue == null) {
                            continue;
                        } else {
                            keys[i] = stringCellValue.trim();
                        }
                    }
                    for (int i = 1; i < totalRowCount; i++) {
                        Map<String, String> rowMap = new HashMap<String, String>(
                                totalColCount);
                        Row row = sheet.getRow(i);
                        if (row == null || row.getCell(0) == null) {
                            continue;
                        }
                        for (int j = 0; j < totalColCount; j++) {
                            String stringCellValue = "";
                            Cell cell = row.getCell(j);
                            String key = keys[j];
                            if (cell == null) {
                                rowMap.put(key, stringCellValue.trim());
                                continue;
                            }
                            int cellType = cell.getCellType();
                            switch (cellType) {
                                case Cell.CELL_TYPE_BOOLEAN:
                                    stringCellValue = ""
                                            + cell.getBooleanCellValue();
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    stringCellValue = ""
                                            + decimalFormat.format(cell.getNumericCellValue());
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    stringCellValue = ""
                                            + cell.getStringCellValue();
                                    break;
                            }
                            if (stringCellValue == null) {
                                stringCellValue = "";
                            }
                            rowMap.put(key, stringCellValue.trim());
                        }
                        list.add(rowMap);
                    }
                    if (!list.isEmpty()) {
                        map.put(sheet.getSheetName(), list);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }

        return map;
    }

    public static Map<String, JSONObject[][]> transXlsToArray(InputStream inputStream){
        return null;
    }

    /**
     * 功能描述:
     * <创建Excel>
     *
     * @param filePath 1
     * @return void
     * @author zhoulipu
     * @date 2019/8/2 17:04
     */
    public static void createExcel(String filePath) {
        FileOutputStream out = null;
        try {
            // 创建工作薄
            Workbook wb = null;
            if (StringUtils.substringAfterLast(filePath, ".").equals("xls")) {
                wb = new HSSFWorkbook();
            }
            if (StringUtils.substringAfterLast(filePath, ".").equals("xlsx")) {
                wb = new XSSFWorkbook();
            }
            if (wb == null) {
                System.out.println("文件后缀不规范，无法操作excel文件，后缀示例：" + "xls" + ", " + "xlsx");
                return;
            }
            out = new FileOutputStream(filePath);
            // 创建工作表
            wb.createSheet();
            // 设置工作表名
            wb.setSheetName(0, "Sheet1");
            wb.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 功能描述:
     * <写入Excel>
     *
     * @param rowList  1
     * @param filePath 2
     * @return void
     * @author zhoulipu
     * @date 2019/8/2 17:38
     */
    public static void writeExcel(List<Object> rowList, String filePath) {
        OutputStream out = null;
        try {
            File file = new File(filePath);
            FileInputStream in = new FileInputStream(file);
            Workbook workbook=WorkbookFactory.create(in);
            Sheet sheet = workbook.getSheetAt(0);
            // 设置单元格属性
            CellStyle cellStyle = workbook.createCellStyle();
            // 水平居中
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // TODO 写入数据
            for (int i = 0; i < rowList.size(); i++) {
                // 创建行
                Row row = sheet.createRow(i);
                List<String> cellList = (ArrayList<String>) rowList.get(i);
                for (int j = 0; j < cellList.size(); j++) {
                    // 创建列
                    Cell cell = row.createCell(j);
                    cell.setCellValue(cellList.get(j));
                    cell.setCellStyle(cellStyle);
                }
            }
            out = new FileOutputStream(filePath);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 功能描述:
     * <删除数据>
     *
     * @param filePath 3
     * @return void
     * @author zhoulipu
     * @date 2019/8/2 17:38
     */
    public static void deleteMessage(String filePath) {
        OutputStream out = null;
        try {
            File file = new File(filePath);
            FileInputStream in = new FileInputStream(file);
            Workbook workbook=WorkbookFactory.create(in);
            // 取第一个Sheet工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 原始数据总行数
            int rowNum = sheet.getLastRowNum() + 1;

            // 删除数据
            for (int i = 1; i <= rowNum; i++) {
                Row row = sheet.getRow(i - 1);
                if (row != null) {
                    sheet.removeRow(row);
                }
            }
            out = new FileOutputStream(filePath);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

