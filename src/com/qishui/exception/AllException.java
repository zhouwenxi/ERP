package com.qishui.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.qishui.file.FileUtils;

/**
 * 
 * @author zhou
 *
 */
public class AllException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 处理异常
	 * 
	 * @param e
	 */
	public static void handle(Exception e) {

		// 打印堆栈信息
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			e.printStackTrace(new PrintStream(baos));
		} finally {
			FileUtils.closeIO(baos);
		}

		// 设置文件名字,路径
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(d);
		String path = "E://log//" + date + ".log";
		FileUtils.createNewFile(path);

		// 打印输出头信息
		StringBuffer err = new StringBuffer();
		err.append(new SimpleDateFormat("HH:mm:ss").format(d));
		err.append(" found the exception ");
		err.append(baos.toString());
		err.append("\n");

		FileUtils.bufferedWriterTxt(path, err.toString(), true);

		// 抛出异常,不执行下面代码
		// throw new RuntimeException(e);
	}

}
