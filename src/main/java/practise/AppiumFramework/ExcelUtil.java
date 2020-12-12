package practise.AppiumFramework;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class ExcelUtil {

	List<String> columns = new ArrayList<String>();
	private Workbook workbook;
	private Sheet activeSheet; 

	private boolean xmlBase;

	public ExcelUtil(String path) {
		FileInputStream inputStream =null;

		try {
			inputStream = new FileInputStream(path);
			if (path.endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(inputStream);
				xmlBase = true;
			} else if (path.endsWith(".xls")) {
				workbook = new HSSFWorkbook(inputStream);
				xmlBase = false;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean setActiveSheet(int sheetIndex) {
		if(xmlBase) {
			((XSSFWorkbook)workbook).setActiveSheet(sheetIndex);
		}else {
			((HSSFWorkbook)workbook).setActiveSheet(sheetIndex);
		}
		return workbook.getActiveSheetIndex()==sheetIndex;
	}

	public Sheet getActiveSheet() {
		return workbook.getSheetAt(workbook.getActiveSheetIndex());
	}

	public boolean setActiveSheet(String sheetName) {
		return setActiveSheet(workbook.getSheetIndex(sheetName));
	}

	public List<Cell> getTextsInColumns(int columnIndex){
		
		List<String> list = new ArrayList<String>();
		List<Cell> list2 = new ArrayList<Cell>();
		Sheet sheet = getActiveSheet();
		int lastRow = sheet.getLastRowNum();
		for(int row =1;row<lastRow;row++) {
			list.add(getCellText(sheet.getRow(row).getCell(columnIndex)));
			list2.add(sheet.getRow(row).getCell(columnIndex));
		}
		
		return list2;
	}

	private String getCellText(Cell cell) {
		DataFormatter dataFormatter = new DataFormatter();
		return dataFormatter.formatCellValue(cell);
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		
		String filePath = "C:\\Users\\vinay\\Documents\\data.xlsx";
		
		ExcelUtil excelUtil = new ExcelUtil(filePath);
		
		System.out.println(excelUtil.getTextsInColumns(2).get(0));
		
		
		
	}

}
