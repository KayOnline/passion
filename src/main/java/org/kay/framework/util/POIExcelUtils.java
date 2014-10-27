package org.kay.framework.util;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * 
 * Handle excel with POI
 * @author Kay
 */
public class POIExcelUtils {

	private static final Logger LOGGER = Logger.getLogger(POIExcelUtils.class);
	
	public static final String SHEET_TEMPLATE = "Template";
	
	public static final String SHEET_DATA_OPTIONS = "DataOptions";
	
	private static final int SHEET_COLUMN_WIDTH = 20 * 256;
	
	private static final String[] ALPHABETS = {
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
			"U", "V", "W", "X", "Y", "Z"
	};
	
	public POIExcelUtils() {
		
	}
	
	// Example
	/*@Test
	public void test() throws Exception {

		// Excel name
		String filename = "workbook.xls";

		// Create workbook with given suffix
		Workbook wb = filename.endsWith(".xls") ? new HSSFWorkbook() : new XSSFWorkbook();

		// Create sheets
		wb.createSheet(WorkbookUtil.createSafeSheetName(SHEET_TEMPLATE));
		wb.createSheet(WorkbookUtil.createSafeSheetName(SHEET_DATA_OPTIONS));
		
		// Create CellStyle For Date and Calendar
		CellStyle cellStyleDateTime = wb.createCellStyle();
		cellStyleDateTime.setDataFormat(wb.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
		
		// Create CellStyle For Title
		CellStyle cellStyleTitle = wb.createCellStyle();
		cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyleTitle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
	    Font font = wb.createFont();
	    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    cellStyleTitle.setFont(font);
	    
	    // Current sheet
	    Sheet sheet = null;
	    

		// Deal With SHEET_TEMPLATE 
		sheet = wb.getSheet(POIExcelUtils.SHEET_DATA_OPTIONS);
		Cell cell = null;
		cell = this.createCell(sheet, 0, 0);
		cell.setCellValue("广东省");
		cell = this.createCell(sheet, 1, 0);
		cell.setCellValue("湖北省");

		cell = this.createCell(sheet, 0, 1);
		cell.setCellValue("广州市");
		cell = this.createCell(sheet, 1, 1);
		cell.setCellValue("云浮市");
		cell = this.createCell(sheet, 2, 1);
		cell.setCellValue("江门市");

		cell = this.createCell(sheet, 0, 2);
		cell.setCellValue("武汉市");
		cell = this.createCell(sheet, 1, 2);
		cell.setCellValue("宜昌市");
		cell = this.createCell(sheet, 2, 2);
		cell.setCellValue("恩施市");
		
	    Name refer1 = wb.createName();  
	    refer1.setRefersToFormula("DataOptions!$A$1:$A$2");  
	    refer1.setNameName("省份");  
	    Name refer2 = wb.createName();  
	    refer2.setRefersToFormula("DataOptions!$B$1:$B$3");  
	    refer2.setNameName("广东省");  
	    Name refer3 = wb.createName();  
	    refer3.setRefersToFormula("DataOptions!$C$1:$C$3");  
	    refer3.setNameName("湖北省");  
	    
	    
		sheet = wb.getSheet(POIExcelUtils.SHEET_TEMPLATE);
		POIExcelUtils.setDataValidations(sheet, "省份", 0, 10, 0, 0);

		for (int i = 0; i < 10; i++) {
			POIExcelUtils.setDataValidations(sheet, "INDIRECT($A$" + (i+1) + ")", i, i, 1, 1);
		}
		
		// Write generated File to local Machine
		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\" + filename);
		wb.write(fileOut);
		fileOut.close();

	}*/
	
	
	@SuppressWarnings("unchecked")
	public static Workbook genExcelWithRowSetDynaClass(String filename, 
				  RowSetDynaClass rsdc, Map<String, String> aliasMap) throws Exception {

		// Create workbook with given suffix
		Workbook wb = filename.endsWith(".xls") ? new HSSFWorkbook() : new XSSFWorkbook();
		
		// Create sheets
		wb.createSheet(WorkbookUtil.createSafeSheetName(SHEET_TEMPLATE));
		wb.createSheet(WorkbookUtil.createSafeSheetName(SHEET_DATA_OPTIONS));
		
		// Create CellStyle For Date and Calendar
		CellStyle cellStyleDateTime = wb.createCellStyle();
		cellStyleDateTime.setDataFormat(wb.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
		
		// Create CellStyle For Title
		CellStyle cellStyleTitle = wb.createCellStyle();
		cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyleTitle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
	    Font font = wb.createFont();
	    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    cellStyleTitle.setFont(font);
	    
	    // Current sheet
	    Sheet sheet = null;
	    
		//// Deal With SHEET_TEMPLATE ////
		sheet = wb.getSheet(POIExcelUtils.SHEET_TEMPLATE);
		
		// Set title row
		DynaProperty[] props = rsdc.getDynaProperties();
		
		for (int t = 0; t < props.length; t++) {
			Cell cell = createCell(sheet, 0, t);
			//根据设定的别名对应关系设置excel标题行
			String titleName = aliasMap.get(props[t].getName()) == null ? props[t].getName() : aliasMap.get(props[t].getName());
			cell.setCellValue(titleName);
			cell.setCellStyle(cellStyleTitle);
		}
		
		// Set data rows
		List<DynaBean> rows = rsdc.getRows();
		
		for (int j = 0; j < props.length; j++) {
			
			for (int i = 0; rows != null && i < rows.size(); i++) {
				
				Cell cell = createCell(sheet, i + 1, j);
				
				Object obj = rows.get(i).get(props[j].getName());
				
				if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Date) {
					cell.setCellValue((Date) obj);
					cell.setCellStyle(cellStyleDateTime);
				} else if (obj instanceof Double) {
					cell.setCellValue((Double) obj);
				} else if (obj instanceof Calendar) {
					cell.setCellValue((Calendar) obj);
					cell.setCellStyle(cellStyleDateTime);
				} else if (obj instanceof Boolean) {
					cell.setCellValue((Boolean) obj);
				} else {
					cell.setCellValue("");
				}
			}
			
			// Set Column Width
			sheet.setColumnWidth(j, SHEET_COLUMN_WIDTH);
		}
		
		//// Deal With SHEET_DATA_OPTIONS ////
		sheet = wb.getSheet(POIExcelUtils.SHEET_TEMPLATE);
		
		LOGGER.info("generate excel successfully.");
		
		return wb;

	}
	
	
	/**
	 * @function
	 * 		1.Create Two Sheet: SHEET_TEMPLATE SHEET_DATA_OPTIONS
	 *      2.Insert Droplist Date into SHEET_DATA_OPTIONS
	 * 		3.Create NamedRange
	 * 		4.Insert Data into SHEET_TEMPLATE
	 * @example
	 *		
	 * @skill
	 *		String[] strs = (String[])list.toArray();
	 */
	@SuppressWarnings("rawtypes")
	public static Workbook generateExcel(String filename, Map<String, 
			List<Object>> droplistMap, List datalist) throws Exception {

		
		// Create workbook with given suffix
		Workbook wb = filename.endsWith(".xls") ? new HSSFWorkbook() : new XSSFWorkbook();
		
		// Create sheets
		wb.createSheet(WorkbookUtil.createSafeSheetName(SHEET_TEMPLATE));
		wb.createSheet(WorkbookUtil.createSafeSheetName(SHEET_DATA_OPTIONS));
		
		// Create CellStyle For Date and Calendar
		CellStyle cellStyleDateTime = wb.createCellStyle();
		cellStyleDateTime.setDataFormat(wb.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
		
		// Create CellStyle For Title
		CellStyle cellStyleTitle = wb.createCellStyle();
		cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyleTitle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
	    Font font = wb.createFont();
	    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    cellStyleTitle.setFont(font);
	    
		
		// Deal With SHEET_DATA_OPTIONS
		for (int i = 0; droplistMap != null && i < droplistMap.keySet().toArray().length; i++) {
			
			String dropName = droplistMap.keySet().toArray()[i].toString();
			
			// Insert Droplists Data into sheet named DataOptions
			for (int j = 0; j < droplistMap.get(dropName).size(); j++) {
				
				Sheet sheet = wb.getSheet(POIExcelUtils.SHEET_DATA_OPTIONS);
				
				Cell cell = createCell(sheet, j, i);
				
				Object obj = droplistMap.get(dropName).get(j);
				
				if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Date) {
					cell.setCellValue((Date) obj);
					cell.setCellStyle(cellStyleDateTime);
				} else if (obj instanceof Double) {
					cell.setCellValue((Double) obj);
				} else if (obj instanceof Calendar) {
					cell.setCellValue((Calendar) obj);
					cell.setCellStyle(cellStyleDateTime);
				} else if (obj instanceof Boolean) {
					cell.setCellValue((Boolean) obj);
				} else {
					cell.setCellValue("");
				}
				
			}
			
			// Named Range is a way to refer to a group of cells by a name.
			Name namedRange = wb.createName();  
			String reference = generateReference(SHEET_DATA_OPTIONS, i + 1, 1, droplistMap.get(dropName).size());
			namedRange.setRefersToFormula(reference);  
			namedRange.setNameName(dropName);
		}
		
		// Deal With SHEET_TEMPLATE
		Sheet sheet = wb.getSheet(POIExcelUtils.SHEET_TEMPLATE);
		
		for (int i = 0; datalist != null && i < datalist.size(); i++) {
			
			// Wrap the DynaBean APIs around an existing standard JavaBean class.
			DynaBean wrapper = new WrapDynaBean(datalist.get(i));
			DynaProperty[] props = wrapper.getDynaClass().getDynaProperties();
			
			for (int j = 0; j < props.length; j++) {
				
				//if(props[j].getName().trim().equals("class")) { continue; }
				
				if (i == 0) {
					// Title Row
					Cell cell0 = createCell(sheet, i, j);
					cell0.setCellValue(props[j].getName());
					cell0.setCellStyle(cellStyleTitle);
					
					// First Data Row
					Cell cell1 = createCell(sheet, i + 1, j);
					Object obj = wrapper.get(props[j].getName());
					if (obj instanceof String) {
						cell1.setCellValue((String) obj);
					} else if (obj instanceof Date) {
						cell1.setCellValue((Date) obj);
						cell1.setCellStyle(cellStyleDateTime);
					} else if (obj instanceof Double) {
						cell1.setCellValue((Double) obj);
					} else if (obj instanceof Calendar) {
						cell1.setCellValue((Calendar) obj);
						cell1.setCellStyle(cellStyleDateTime);
					} else if (obj instanceof Boolean) {
						cell1.setCellValue((Boolean) obj);
					} else {
						cell1.setCellValue("");
					}
				} else {
					Cell cell = createCell(sheet, i, j);
					Object obj = wrapper.get(props[j].getName());
					if (obj instanceof String) {
						cell.setCellValue((String) obj);
					} else if (obj instanceof Date) {
						cell.setCellValue((Date) obj);
						cell.setCellStyle(cellStyleDateTime);
					} else if (obj instanceof Double) {
						cell.setCellValue((Double) obj);
					} else if (obj instanceof Calendar) {
						cell.setCellValue((Calendar) obj);
						cell.setCellStyle(cellStyleDateTime);
					} else if (obj instanceof Boolean) {
						cell.setCellValue((Boolean) obj);
					} else {
						cell.setCellValue("");
					}
				}
				
				// Set Column Width
				sheet.setColumnWidth(j, SHEET_COLUMN_WIDTH);
			}

		}
		
		return wb;

	}
	
	/**
	 * @Description
	 * 	生成Excel单元格的引用公式
	 * @Example
	 * 	generateReference('mysheet', 1, 1, 3); //output: 'mysheet'!$A$1:$A$3
	 */
	public static String generateReference(String sheetName, int col, int firstRow, int lastRow) {
		String colAlphabet = "$" + getColumnAlphabet(col);
		String rowBegin = "$" + firstRow;
		String rowEnd = "$" + lastRow;
		String reference = "'" + sheetName + "'!" + colAlphabet + rowBegin + ":" + colAlphabet + rowEnd;
		return reference;
	}
	
	/**
	 * @Descsription
	 * 	获取Excel列对应的序号
	 * @Example
	 * 	System.out.println(getColumnIndex("ABC"));
	 */
	public static String getColumnIndex(String str) {
		
		int scale = str.length() - 1;
		int coefficient  = 1;
		double sum = 0;
		for (int i = 0; i < str.length(); i++) {
			String alphabet = str.substring(i, i + 1);
			coefficient = Arrays.asList(ALPHABETS).indexOf(alphabet) + 1;
			sum += coefficient * Math.pow(ALPHABETS.length, scale - i);
		}
		return Long.toString((long) sum);
	}
	
	/**
	 * @Descsription
	 * 	获取Excel列对应的字母
	 * @Example
	 * 	System.out.println(getColumnIndex(27));
	 */
	public static String getColumnAlphabet(int value) {
		
		int alphabetsSize = ALPHABETS.length;
		
		StringBuffer sb = new StringBuffer();
		
		int remainder = value % alphabetsSize;
		if (remainder > 0) {
			sb.append(ALPHABETS[remainder - 1]);
		}
		
		while (true) {
			int tmp = value / alphabetsSize;
			if (tmp > alphabetsSize) {		
				sb.append(ALPHABETS[tmp % alphabetsSize - 1]);
				value = tmp;
			} else if (tmp < alphabetsSize && tmp > 0) {
				sb.append(ALPHABETS[tmp - 1]);
				break;
			} else {
				break;
			}
		}
		
		return sb.reverse().toString();
	}
	
	/**
	 * @Description
	 * 	创建Excel单元格
	 * @Example
	 * 	Cell cell = createCell(sheet, 0, 0); // A1
	 * */
	public static Cell createCell(Sheet sheet, int rowIndex, int colIndex) {
		// Create row
		Row row = sheet.getRow(rowIndex);
		row = row == null ? sheet.createRow(rowIndex) : sheet.getRow(rowIndex);
		// Create cell
		Cell cell = row.getCell(colIndex);
		cell = cell == null ? row.createCell(colIndex) : row.getCell(colIndex);
		return cell;
	}
	
	/**
	 * @Descsription
	 * 	设置Excel数据有效性
	 * @example
	 *	setDataValidations(sheet, "省份", 0, 10, 0, 0);
	 * */
	public static void setDataValidations(Sheet sheet, String listFormula, int firstRow, int lastRow, int fisrtCol, int lastCol) {
		CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, fisrtCol, lastCol);
		DataValidationHelper dvHelper = sheet.getDataValidationHelper();
		DataValidationConstraint dvConstraint = dvHelper.createFormulaListConstraint(listFormula);
		DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
		sheet.addValidationData(validation);
	}
	
}
