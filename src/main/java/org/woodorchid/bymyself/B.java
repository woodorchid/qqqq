package org.woodorchid.bymyself;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 韩志雄
 * @date 2023/5/30 22:50
 */
public class B {
	public static void main(String[] args) throws IOException {
		String path = "";
		FileInputStream fileInputStream = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFCell cell = sheet.getRow(0).getCell(0);

		XSSFWorkbook wbs = new XSSFWorkbook();
		XSSFSheet sheet1 = wbs.createSheet();
		int rows=0;
		int cols=0;
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet1.createRow(i);
			for (int j = 0; j < cols; j++) {
				XSSFCell cell1 = row.createCell(j);

			}
		}
		sheet1.addMergedRegion(new CellRangeAddress(0,1,0,2));
	}
}
