package com.Vtiger.GenericLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility
{

	public String readDatafromExcel (int row,int cell,String sheetname) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IAutoConstant.EXCEL_PATH);

		Workbook wb=WorkbookFactory.create(fis);

		String data = wb.getSheet(sheetname).getRow(row).getCell(cell).toString();
		return  data;
	}
}
	
	
