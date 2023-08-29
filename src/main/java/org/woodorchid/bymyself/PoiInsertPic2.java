package org.woodorchid.bymyself;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 韩志雄
 * @date 2023/5/30 23:10
 */
public class PoiInsertPic2 {

	public static void insertPicCost(Workbook workBook, Sheet topPic, String pic){
		BufferedImage bufferImg = null;//图片
		try {
			// 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			//将图片读到BufferedImage
			bufferImg = ImageIO.read(new File(pic));
			// 将图片写入流中
			ImageIO.write(bufferImg, "png", byteArrayOut);
			// 利用HSSFPatriarch将图片写入EXCEL
			XSSFDrawing patriarch = (XSSFDrawing) topPic.createDrawingPatriarch();
			//图片一导出到单元格B2中
			XSSFClientAnchor anchor = new XSSFClientAnchor(3, 3, 5, 5,
					(short) 1, 2, (short) 2, 9);
			// 插入图片
			patriarch.createPicture(anchor, workBook.addPicture(byteArrayOut
					.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
			System.out.println("插入成功" );
		} catch (IOException io) {
			io.printStackTrace();
			System.out.println("插入失败 : " + io.getMessage());
		} finally {

		}
	}

	public static void main(String[] args) {
		try {
			String pic1 = "C:\\Users\\韩志雄\\Desktop\\无标题.png";
			Workbook workBook = new XSSFWorkbook();
			Sheet topPic = workBook.createSheet("top20图");
			String savePath = "C:\\Users\\韩志雄\\Desktop\\无标题 - 副本.xlsx";
			File file = new File(savePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			insertPicCost(workBook,topPic,pic1);
			FileOutputStream os = new FileOutputStream(savePath);
			workBook.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}