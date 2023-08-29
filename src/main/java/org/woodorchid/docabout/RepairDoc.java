package org.woodorchid.docabout;

import java.io.*;
import java.util.regex.Pattern;

/**
 * @author 韩志雄
 * @date 2023/6/7 17:52
 */
public class RepairDoc {
	public static void main(String[] args) throws IOException {
		String path = "D:\\java\\woodorchids\\qqqq\\src\\main\\java\\org\\woodorchid\\docabout\\test.md";
		String toPath = "D:\\java\\woodorchids\\qqqq\\src\\main\\java\\org\\woodorchid\\docabout\\to.md";

		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toPath));
		String line = null;
		String aline = null;
		while ((aline = line = bufferedReader.readLine()) != null){

			line = line.replaceAll("\\(.*\\)","");
			if(line == null){
				line = aline;
			}
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}

	}
}
