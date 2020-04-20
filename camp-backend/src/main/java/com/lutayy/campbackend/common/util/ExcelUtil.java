package com.lutayy.campbackend.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
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
}

