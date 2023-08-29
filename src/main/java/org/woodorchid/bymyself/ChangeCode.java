package org.woodorchid.bymyself;

import java.io.*;

/**
 * @author 韩志雄
 * @date 2023/6/1 23:06
 */
public class ChangeCode {

	public static void main(String[] args) throws IOException {
		String readPath = "C:\\Users\\韩志雄\\Desktop\\designpattern";
		loop(new File(readPath));
	}

	public static void loop(File file) throws IOException {
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				loop(files[i]);
			}
		} else {
			p(file);
		}
	}

	public static void p(File file) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		String absolutePath = file.getAbsolutePath();
		String tmp = absolutePath
				.replace("designpattern","designpattern_copy");
		String wDir = tmp.substring(0, tmp.lastIndexOf(File.separatorChar));
		File file1 = new File(wDir+File.separatorChar+file.getName());
		if(!file1.exists()){
			file1.createNewFile();
		}

		BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(wDir+File.separatorChar+file.getName()),"UTF-8"));
		String line;
		while ((line = bufferedReader.readLine()) != null){
			if(line.startsWith("import")){
				line = line.replace("mulan","woodorchid");
			}
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}
		System.out.println(file.getName()+"修改完成！");

	}

}
