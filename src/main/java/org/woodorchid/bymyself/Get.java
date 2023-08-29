package org.woodorchid.bymyself;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 韩志雄
 * @date 2023/5/30 23:18
 */
public class Get {

	private static final String PATH = "C:\\Users\\韩志雄\\Desktop\\qqas";
	private static final String NEW_PATH = "C:\\Users\\韩志雄\\Desktop\\重置后的图片";
	private static final String OLD_DIR_NAME = "qqas";
	private static final String NEW_DIR_NAME = "重置后的图片";

	private static final Integer BH = 13;
	private static final Integer BW = 20;

	private static final Integer H = 13;
	private static final Integer W = 20;

	private static List<String> users = null;

	public static void main(String[] args) throws IOException {
		//重置图片尺寸
//		resize(new File(PATH));
		users = new ArrayList<>();
		users.add("朱彦军");
		users.add("白利红");
		users.add("刘欣欣");
		users.add("李娜");
		users.add("韩帅清");
		users.add("闫晓春");
		users.add("韩嘉甜");
		users.add("裴燕梅");
		users.add("牛文娟");
		createWorkBook(new File(PATH));
	}

	public static void resize(File file) throws IOException {
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				resize(files[i]);
			}
		}else if(file.getName().contains("现场")){
			process(file,BH,BW);
		}else {
			process(file,H,W);
		}
	}

	public static void process(File file,Integer h, Integer w) throws IOException {
		BufferedImage image = ImageIO.read(file);
		BufferedImage newImage = ImageUtils.resizeImage(image, w, h);
		String dirName = file.getAbsolutePath().replace(File.separatorChar+file.getName(), "");
		String newPath = dirName.replace(OLD_DIR_NAME, NEW_DIR_NAME);
		File dir = new File(newPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		ImageIO.write(newImage, "png", new File(newPath+File.separatorChar+file.getName()));
	}

	private static void createWorkBook(File file) {
		File[] files = file.listFiles();
		XSSFWorkbook wbs = new XSSFWorkbook();
		XSSFSheet sheet = wbs.createSheet();
		for (int i = 0; i < files.length; i++) {

			File[] files1 = files[i].listFiles();
			ArrayList<File> list = new ArrayList<>(Arrays.asList(files1));
			//微信人员
			List<String> wechatUsers = list.stream()
					.filter(e -> !e.getName().contains("现场"))
					.map(e -> e.getName().substring(0,e.getName().lastIndexOf("."))).collect(Collectors.toList());
			List<String> attendUsers = new ArrayList<>(users);

			//现场人员
			attendUsers.removeAll(wechatUsers);

			//
			List<File> attendPics = list.stream().filter(e -> e.getName().contains("现场")).collect(Collectors.toList());
			List<File> wechatPics = list.stream().filter(e -> !e.getName().contains("现场")).collect(Collectors.toList());

			/**
			 * 日期
			 */
			for (int j = 0; j < 2; j++) {
				XSSFRow row = null;
				if(i==0){
					row = sheet.createRow(j);
				}else {
					row = sheet.getRow(j);
				}

				for (int k = 3*i; k < 3*i+3; k++) {
					row.createCell(k);
				}
			}
			//合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 3*i, 3*i+2));
			//设置日期
			System.out.println("设置日期："+i);
			sheet.getRow(0).getCell(3*i).setCellValue(files[i].getName());

			/**
			 *
 			 */
			XSSFRow categoryRow = null;
			if(i==0){
				categoryRow = sheet.createRow(2);
			}else {
				categoryRow = sheet.getRow(2);
			}
			categoryRow.createCell(3*i).setCellValue("类别");
			categoryRow.createCell(3*i+1).setCellValue("姓名");
			categoryRow.createCell(3*i+2).setCellValue("图片");

			/**
			 *现场参加
			 */
			for (int j = 3; j < attendUsers.size()+3; j++) {
				XSSFRow row = null;
				if (i == 0) {
					row = sheet.createRow(j);
					row.setHeight((short) 600);
				}else {
					row = sheet.getRow(j);
				}
				for (int k = 3*i; k < 3*i+3; k++) {
					XSSFCell cell = row.createCell(k);
					if(j==3&&k==3*i){
						cell.setCellValue("现场参加");
					}else if(k==3*i+1){
						System.out.println("这是："+i+","+j+","+k+"->"+" -- "+attendUsers.toString());
						cell.setCellValue(attendUsers.get(j-3));
					}
				}
			}
			sheet.addMergedRegion(new CellRangeAddress(3,attendUsers.size()+2,3*i+2,3*i+2));
			sheet.addMergedRegion(new CellRangeAddress(3,attendUsers.size()+2,3*i,3*i));
			sheet.setColumnWidth(3*i+2,256*16);
			for (int j = 0; j < attendPics.size(); j++) {
				File file1 = attendPics.get(j);
				String absolutePath = file1.getAbsolutePath();
				int i1 = 3 +(j+1)*(attendUsers.size() / attendPics.size());
				insertPicCost(wbs,sheet,absolutePath,3*i+2,3,3*i+3,i1);
			}

			/**
			 *微信
			 */
			for (int j = 3+attendUsers.size(); j < 3+users.size(); j++) {
				XSSFRow row = null;
				if (i == 0) {
					row = sheet.createRow(j);
					row.setHeight((short) 600);
				}else {
					row = sheet.getRow(j);
				}
				for (int k = 3*i; k < 3*i+3; k++) {
					XSSFCell cell = row.createCell(k);
					if(j==3+attendUsers.size()&&k==3*i){
						cell.setCellValue("微信");
					}else if(k==3*i+1){
						cell.setCellValue(wechatUsers.get(j-3-attendUsers.size()));
					}
				}
			}
			sheet.addMergedRegion(new CellRangeAddress(3+attendUsers.size(),users.size()+2,3*i,3*i));
			for (int j = 0; j < wechatPics.size(); j++) {
				File file1 = wechatPics.get(j);
				String absolutePath = file1.getAbsolutePath();

				insertPicCost(wbs,sheet,absolutePath,3*i+2,3+attendUsers.size()+j,3*i+3,4+attendUsers.size()+j);
			}
		}
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream("C:\\Users\\韩志雄\\Desktop\\无标题.xlsx");
			//输出
			wbs.write(fileOutputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//关闭流
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("表格生成完毕！");
	}

	public static void insertPicCost(Workbook workBook, Sheet topPic, String pic,int col1, int row1,int col2,int row2){
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
			XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0,
					(short) col1, row1, (short) col2, row2);
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
}
