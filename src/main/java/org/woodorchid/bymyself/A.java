package org.woodorchid.bymyself;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author 韩志雄
 * @date 2023/5/30 22:23
 */
public class A {
	static final String EXT = ".png";
	public static void main(String[] args) throws IOException {
		Integer width= 0;
		Integer height= 0;

		String root = "";
		File file = new File(root);
//		parse(file);
	}
	public static void parse(File file) throws IOException {
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				parse(files[i]);
			}
		}else if(file.getName().endsWith(".png")){
			BufferedImage image = ImageIO.read(file);
			BufferedImage newImage = ImageUtils.resizeImage(image, 100, 100);
			ImageIO.write(newImage, "png", new File(file.getAbsolutePath().replace(EXT,"_resize"+EXT)));
		}
	}


}
