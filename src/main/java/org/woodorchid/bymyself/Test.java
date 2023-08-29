package org.woodorchid.bymyself;

import java.io.File;

/**
 * @author 韩志雄
 * @date 2023/5/30 22:24
 */
public class Test {
	public static void main(String[] args) {
		//通过url获取BufferedImage图像缓冲区
		File img = new File("C:\\Users\\韩志雄\\Desktop\\无标题.png");
		System.out.println(img.getAbsolutePath());
//			BufferedImage image = ImageIO.read(img);
//			//获取图片的宽、高
//			System.out.println("Width: " + image.getWidth());
//			System.out.println("Height: " + image.getHeight());
//			//调整图片大小为 400X400尺寸
//			BufferedImage newImage = ImageUtils.resizeImage(image,400,400);
//			//将缓冲区图片保存到 F:/test/pic1.jpg (文件不存在会自动创建文件保存，文件存在会覆盖原文件保存)
//			ImageIO.write(newImage, "jpg", new File("C:\\Users\\韩志雄\\Desktop\\pic2.jpg"));
	}
}
