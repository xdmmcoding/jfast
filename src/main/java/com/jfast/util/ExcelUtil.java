package com.jfast.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jfast.vo.ExcelDataVo;

public class ExcelUtil {
	 
    /**
     * 方法名：exportExcel
     * 功能：导出Excel
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 16:00
     * 修改人：
     * 修改描述：
     * 修改时间：
     * @throws Exception 
     */
    public static void exportExcel(HttpServletResponse response, ExcelDataVo data,String fileName) throws Exception {
    	HSSFWorkbook workbook = null;
    	try {
    		//实例化HSSFWorkbook
            workbook = new HSSFWorkbook();
            //创建一个Excel表单，参数为sheet的名字
            HSSFSheet sheet = workbook.createSheet("sheet");
            //设置表头
            setTitle(workbook, sheet, data.getHead());
            //设置单元格并赋值
            setData(sheet, data.getRows());
            //设置浏览器下载
            setBrowser(response, workbook, fileName);
		} catch (Exception e) {
			
		} finally {
			 workbook.close();
		}
    }
 
    /**
     * 方法名：setTitle
     * 功能：设置表头
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 10:20
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, String[] str) {
    	 HSSFRow row = sheet.createRow(0);
         //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
         for (int i = 0; i <= str.length; i++) {
             sheet.setColumnWidth(i, 15 * 256);
         }
         //设置为居中加粗,格式化时间格式
         HSSFCellStyle style = workbook.createCellStyle();
         HSSFFont font = workbook.createFont();
         font.setBold(true);
         style.setFont(font);
         style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
         //创建表头名称
         HSSFCell cell;
         for (int j = 0; j < str.length; j++) {
             cell = row.createCell(j);
             cell.setCellValue(str[j]);
             cell.setCellStyle(style);
         }
    }
 
    /**
     * 方法名：setData
     * 功能：表格赋值
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 16:11
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    private static void setData(HSSFSheet sheet, List<String[]> data) {
    	 int rowNum = 1;
         for (int i = 0; i < data.size(); i++) {
             HSSFRow row = sheet.createRow(rowNum);
             for (int j = 0; j < data.get(i).length; j++) {
                 row.createCell(j).setCellValue(data.get(i)[j]);
             }
             rowNum++;
         }
    }
 
    /**
     * 方法名：setBrowser
     * 功能：使用浏览器下载
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 16:20
     * 修改人：
     * 修改描述：
     * 修改时间：
     * @throws IOException 
     */
    private static void setBrowser(HttpServletResponse response, HSSFWorkbook workbook, String fileName) throws Exception {
    	OutputStream os = null;
    	try {
    		//清空response
            response.reset();
            //设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
            os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
		} catch (Exception e) {
			
		} finally {
	        if(os != null)os.close();
		}
    }
    public static void exportExcelXlsx(HttpServletResponse response, ExcelDataVo data,String fileName) throws Exception {
    	XSSFWorkbook workbook = null;
    	try {
    		//实例化HSSFWorkbook
    		workbook = new XSSFWorkbook();
    		//创建一个Excel表单，参数为sheet的名字
    		XSSFSheet sheet = workbook.createSheet("sheet");
    		//设置表头
    		setTitleXlsx(workbook, sheet, data.getHead());
    		//设置单元格并赋值
    		setDataXlsx(sheet, data.getRows());
    		//设置浏览器下载
    		setBrowserXlsx(response, workbook, fileName);
    	} catch (Exception e) {
    		
    	} finally {
    		workbook.close();
    	}
    }
    /**
     * 方法名：setTitle
     * 功能：设置表头
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 10:20
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    private static void setTitleXlsx(XSSFWorkbook workbook, XSSFSheet sheet, String[] str) {
    	XSSFRow row = sheet.createRow(0);
    	//设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
    	for (int i = 0; i <= str.length; i++) {
    		sheet.setColumnWidth(i, 15 * 256);
    	}
    	//设置为居中加粗,格式化时间格式
    	XSSFCellStyle style = workbook.createCellStyle();
    	XSSFFont font = workbook.createFont();
    	font.setBold(true);
    	style.setFont(font);
    	style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
    	//创建表头名称
    	XSSFCell cell;
    	for (int j = 0; j < str.length; j++) {
    		cell = row.createCell(j);
    		cell.setCellValue(str[j]);
    		cell.setCellStyle(style);
    	}
    }
    
    /**
     * 方法名：setData
     * 功能：表格赋值
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 16:11
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    private static void setDataXlsx(XSSFSheet sheet, List<String[]> data) {
    	int rowNum = 1;
    	for (int i = 0; i < data.size(); i++) {
    		XSSFRow row = sheet.createRow(rowNum);
    		for (int j = 0; j < data.get(i).length; j++) {
    			row.createCell(j).setCellValue(data.get(i)[j]);
    		}
    		rowNum++;
    	}
    }
    
    /**
     * 方法名：setBrowser
     * 功能：使用浏览器下载
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 16:20
     * 修改人：
     * 修改描述：
     * 修改时间：
     * @throws IOException 
     */
    private static void setBrowserXlsx(HttpServletResponse response, XSSFWorkbook workbook, String fileName) throws Exception {
    	OutputStream os = null;
    	try {
    		//清空response
    		response.reset();
    		//设置response的Header
    		response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
    		os = new BufferedOutputStream(response.getOutputStream());
    		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    		//将excel写入到输出流中
    		workbook.write(os);
    		os.flush();
    	} catch (Exception e) {
    		
    	} finally {
    		if(os != null)os.close();
    	}
    }
 
 
    /**
     * 方法名：importExcel
     * 功能：导入
     * 描述：
     * 创建人：typ
     * 创建时间：2018/10/19 11:45
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    public static List<Object[]> importExcel(InputStream inputStream) {
    	Workbook workbook = null;
        try {
            List<Object[]> list = new ArrayList<>();
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //获取sheet的行数
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rows; i++) {
                //过滤表头行
                if (i == 0) {
                    continue;
                }
                //获取当前行的数据
                Row row = sheet.getRow(i);
                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                int index = 0;
                for (Cell cell : row) {
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        objects[index] = (int) cell.getNumericCellValue();
                    }
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        objects[index] = cell.getStringCellValue();
                    }
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
                        objects[index] = cell.getBooleanCellValue();
                    }
                    if (cell.getCellType()== HSSFCell.CELL_TYPE_ERROR) {
                        objects[index] = cell.getErrorCellValue();
                    }
                    index++;
                }
                list.add(objects);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
        	try {
				if(workbook != null) workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return null;
    }
}

