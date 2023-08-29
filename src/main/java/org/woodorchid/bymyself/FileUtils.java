package org.woodorchid.bymyself;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 韩志雄
 * @date 2023/5/30 23:44
 */
public class FileUtils {
	/**
	 * 根据文件夹路径实现文件夹的复制（复制文件夹中的所有文件）
	 *
	 * @param sourceFolderPath
	 * @param targetFolderPath
	 */
	public static void copyFolder(String sourceFolderPath, String targetFolderPath) {
		File sourceFolder = new File(sourceFolderPath);
		String[] sourceFilePathList = sourceFolder.list();
		File targetFolder = new File(targetFolderPath);
		//判断目标文件夹是否存在,没有则创建
		if (!targetFolder.exists()) {
			targetFolder.mkdirs();
		}
		try {
			for (String filePath : sourceFilePathList) {
				if (new File(sourceFolderPath + sourceFolder.separator + filePath).isDirectory()) {
					copyFolder(sourceFolderPath + sourceFolder.separator + filePath, targetFolderPath + sourceFolder.separator + filePath);
				}
				if (new File(sourceFolderPath + sourceFolder.separator + filePath).isFile()) {
					copyFile(sourceFolderPath + sourceFolder.separator + filePath, targetFolderPath + sourceFolder.separator + filePath);
				}
			}
		} catch (IOException e) {
			System.out.println("复制文件夹信息出错");
		}
	}

	public static void copyFile(String sourceFilePath, String targetFilePath) throws IOException {
		File sourceFile = new File(sourceFilePath);
		File targetFile = new File(targetFilePath);
		FileInputStream inputStream = new FileInputStream(sourceFile);
		FileOutputStream outputStream = new FileOutputStream(targetFile);
		byte[] buffer = new byte[4096];
		int length = 0;
		while ((length = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}
}
