package cn.letcode.utils.file;

import java.io.File;

import cn.letcode.utils.file.FileUtil;

/**
 * 删除长文件
 * 
 * @author chensj
 *
 */
public class FileUtilTest {

	public static void main(String[] args) {
		String filePath = "D:\\tools\\eclipse";
		File file = new File(filePath);
		FileUtil.deleteLongFile(file);
	}

}
