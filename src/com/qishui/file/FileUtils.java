package com.qishui.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

import com.qishui.exception.AllException;

//Linux  文件分隔符  正斜杠  /   反斜杠   win   正斜杠/   反 \\     
//绝对路径:完整路径
//相对路径:  . 当前  .. 上一级目录  同一级目录，同一盘符，可以使用相对路径

public class FileUtils {

	/**
	 * 文件是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static Boolean exists(String path) {

		// 可以使用整体;也可以分开，父目录子目录。
		return new File(path).exists();

	}

	/**
	 * 创建一个新目录
	 * 
	 * @param dir
	 * @return
	 */
	public static Boolean createDir(String dir) {

		File f = new File(dir);
		if (!f.exists()) {
			return f.mkdirs();
		}

		return false;
	}

	/**
	 * 创建一个新文件
	 * 
	 * @param path
	 * @return
	 */
	public static Boolean createNewFile(String path) {

		File f = new File(path);
		if (!f.exists() && !f.isDirectory()) {
			try {
				// 判断上级目录是否存在,不存在的话，创建目录
				createDir(f.getParent());
				return f.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return false;

	}

	/**
	 * 创建新文件 重载
	 * 
	 * @param dir
	 * @param path
	 * @return
	 */
	public static Boolean createNewFile(String dir, String path) {

		// 创建目录
		createDir(dir);

		File f = new File(dir, path);
		if (!f.exists() && !f.isDirectory()) {
			try {
				return f.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return false;

	}

	/**
	 * 删除文件
	 * 
	 * @return
	 */
	public static Boolean deleteFile(String pathName) {

		File f = new File(pathName);

		if (f.exists() && f.isFile()) {
			return f.delete();
		}

		return false;

	}

	/**
	 * 删除目录
	 * 
	 * @param dir
	 * @return
	 */
	public static boolean deleteDir(String dir) {
		File file = new File(dir);
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				deleteDir(children[i].getAbsolutePath());
			}
		}
		// 空目录和文件此时删除
		return file.delete();
	}

	/**
	 * 删除目录
	 * 
	 * @param dir
	 * @return
	 */

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				deleteDir(new File(dir, children[i]));
			}
		}
		// 空目录和文件此时删除
		return dir.delete();
	}

	/**
	 * 遍历文件列表,查找，删除目录文件可以使用
	 * 
	 * @param args
	 */
	public static void list(String dir) {

		File file = new File(dir);

		if (file.isDirectory()) {
			// System.out.println("文件目录: " + file.getAbsolutePath());
			File[] fileItem = file.listFiles();
			for (File f : fileItem) {
				list(f.getAbsolutePath());
			}
		} else {
			if (file.getName().endsWith(".md")) {

				System.out.println(file.getAbsolutePath());
				// copyBufferedFile(file.getAbsolutePath(),
				// "C:\\Users\\zhou\\Desktop\\Android基础知识\\" + "_"+file.getName()+"_"+
				// MD5Util.encrypt(file.getAbsolutePath())+".md");
			}
		}
	}

	/**
	 * 读取数据 字节流读取文本数据
	 * 
	 * @param path
	 */
	public static String readTxt(String path) {

		FileInputStream fileInputStream = null;
		// 存取字符串
		StringBuffer stringBuffer = new StringBuffer();

		try {
			// 找到文件
			File f = new File(path);
			// 建立通道
			fileInputStream = new FileInputStream(f);
			// 读取数据 read -1 文本结束
			byte buf[] = new byte[1024];
			int length = 0;
			while ((length = fileInputStream.read(buf)) != -1) {
				stringBuffer.append(new String(buf, 0, length));
			}

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// 关闭资源
			closeIO(fileInputStream);
		}

		return stringBuffer.toString();

	}

	/**
	 * 缓冲输入字节流读文本取数据
	 * 
	 * @param path
	 * @return
	 */
	public static String readBuffTxt(String path) {

		BufferedInputStream bufferedInputStream = null;
		StringBuffer stringBuffer = new StringBuffer();
		byte[] buffer = new byte[1024];
		try {
			bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(path)));
			int length = 0;
			while ((length = bufferedInputStream.read(buffer)) != -1) {
				stringBuffer.append(new String(buffer, 0, length));
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// BufferedInputStream 已经包含关闭FileInputStream
			closeIO(bufferedInputStream);
		}

		return stringBuffer.toString();
	}

	/**
	 * 保存数据 字节流保存文本数据
	 * 
	 * @param path
	 */
	public static void writeTxt(String path, String value) {

		// 建立文件联系
		File file = new File(path);
		FileOutputStream outputStream = null;
		try {
			// 建立输出通道
			// true 文本追加数据
			// outputStream = new FileOutputStream(file,true);
			outputStream = new FileOutputStream(file);
			// 写数据
			byte[] buf = value.getBytes();
			outputStream.write(buf);

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// 关闭输出资源
			closeIO(outputStream);
		}

	}

	/**
	 * 缓冲输入字节流读文本取数据
	 * 
	 * @param path
	 * @return
	 */
	public static void writeBuffTxt(String path, String value) {

		// 创建文件
		createNewFile(path);

		BufferedOutputStream bufferedOutputStream = null;
		try {
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(path)));
			bufferedOutputStream.write(value.getBytes());
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// BufferedOutputStream 已经包含关闭FileOutputStream
			closeIO(bufferedOutputStream);
		}

	}

	/**
	 * 复制文件，字节流，不需要转换的字节;但是读取文本数据可能乱码。
	 * 
	 * @param srcPath
	 * @param newPath
	 */
	public static void copyFile(String srcPath, String newPath) {
		FileOutputStream outputStream = null;
		FileInputStream inputStream = null;
		try {
			// 创建文件
			createNewFile(newPath);
			// 输出通道
			outputStream = new FileOutputStream(new File(newPath));
			// 输入通道
			inputStream = new FileInputStream(new File(srcPath));
			// 读取数据
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, length);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// 关闭资源
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (inputStream != null)
						inputStream.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}

	}

	/**
	 * 使用缓冲字节复制文件
	 * 
	 * @param srcPath
	 * @param newPath
	 */
	public static void copyBufferedFile(String srcPath, String newPath) {
		BufferedOutputStream outputStream = null;
		BufferedInputStream inputStream = null;
		try {
			// 创建文件
			createNewFile(newPath);
			// 输出通道
			outputStream = new BufferedOutputStream(new FileOutputStream(new File(newPath)));
			// 输入通道
			inputStream = new BufferedInputStream(new FileInputStream(new File(srcPath)));
			// 读取数据
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, length);
			}
			System.out.println("已经从" + srcPath + "复制到了" + newPath + ".");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// 关闭资源
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (inputStream != null)
						inputStream.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}

	}

	/**
	 * 字符读取 防止中文乱码,将原来是GBK的转成UTF-8的
	 * 
	 * @param path
	 */
	public static String readerTxt(String path) {

		File file = new File(path);
		FileReader fileReader = null;
		StringBuffer stringBuffer = new StringBuffer();

		try {
			fileReader = new FileReader(file);
			int length = 0;
			char[] ch = new char[1024];
			while ((length = fileReader.read(ch)) != -1) {
				stringBuffer.append(new String(ch, 0, length));
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeIO(fileReader);
		}
		return stringBuffer.toString();

	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static String bufferedReaderTxt(String path) {

		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = new StringBuffer();

		try {
			bufferedReader = new BufferedReader(new FileReader(new File(path)));
			int length = 0;
			char[] ch = new char[1024];
			while ((length = bufferedReader.read(ch)) != -1) {
				stringBuffer.append(new String(ch, 0, length));
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeIO(bufferedReader);
		}
		return stringBuffer.toString();

	}

	/**
	 * 按行读取文本数据
	 * 
	 * @param path
	 * @return
	 */
	public static String bufferedReaderTxtByLine(String path) {

		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = new StringBuffer();
		String line;
		int index = 0;
		try {
			bufferedReader = new BufferedReader(new FileReader(new File(path)));

			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(++index).append("\t").append(line).append("\n");
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeIO(bufferedReader);
		}
		return stringBuffer.toString();

	}

	/**
	 * 按行读取文本数据
	 * 
	 * @param path
	 * @return
	 */
	public static String bufferedReaderTxtByMyLine(String path) {

		FileReader fileReader = null;
		StringBuffer stringBuffer = new StringBuffer();
		String line;
		int index = 0;
		try {
			fileReader = new FileReader(new File(path));

			while ((line = readLine(fileReader)) != null) {
				stringBuffer.append(++index).append("\t").append(line).append("\n");
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeIO(fileReader);
		}
		return stringBuffer.toString();

	}

	/**
	 * 写字符到文件
	 * 
	 * @param path
	 * @param value
	 */
	public static void writerTxt(String path, String value, boolean append) {

		File file = new File(path);
		FileWriter fileWriter = null;

		try {
			// 自动创建目标文件,有文件内容，会先清空;
			// fileWriter = new FileWriter(file);
			// 自动创建目标文件,有文件内容，追加数据
			fileWriter = new FileWriter(file, append);
			fileWriter.write(value);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeIO(fileWriter);
		}

	}

	/**
	 * 复制一个文本文件;不能复制图片。
	 * 
	 * @param src
	 * @param newPath
	 */
	public static void copyFileTxt(String src, String newPath) {

		FileWriter fileWriter = null;
		FileReader fileReader = null;

		try {
			fileWriter = new FileWriter(new File(newPath));
			fileReader = new FileReader(new File(src));

			char[] ch = new char[1024];
			int length = 0;
			while ((length = fileReader.read(ch)) != -1) {
				fileWriter.write(ch, 0, length);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {

			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {

				try {
					if (fileReader != null)
						fileReader.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

		}

	}

	/**
	 * 缓冲字符输出
	 * 
	 * @param path
	 * @param value
	 * @param append
	 */
	public static void bufferedWriterTxt(String path, String value, boolean append) {

		BufferedWriter bufferedWriter = null;

		try {
			FileWriter fileWriter = new FileWriter(new File(path), append);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(value);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeIO(bufferedWriter);
		}

	}

	/**
	 * 读取行
	 * 
	 * @param fileReader
	 * @return
	 * @throws IOException
	 */
	public static String readLine(FileReader fileReader) throws IOException {

		StringBuffer stringBuffer = new StringBuffer();
		int length = 0;
		// 有文本数据
		while ((length = fileReader.read()) != -1) {

			if ((char) length == '\r') {
				continue;
			} else if ((char) length == '\n') {
				// 结束
				break;
			} else {
				stringBuffer.append((char) length);
			}

		}
		// 无文本数据,结束时
		if (length == -1) {
			return null;
		}

		return stringBuffer.toString();

	}

	/**
	 * 关闭资源,不占用使用操作过得资源
	 * 
	 * @param closeables
	 */
	public static void closeIO(Closeable... closeables) {

		if (closeables == null) {
			return;
		}

		for (Closeable c : closeables) {
			if (c != null) {
				try {
					c.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * 文件合并,输入流合并
	 * 
	 * @param newPath 输出流
	 * @param src     输入流文件
	 */
	public static void merge(String newPath, String... src) {

		// 保存输入流
		Vector<FileInputStream> v = new Vector<FileInputStream>();
		SequenceInputStream sequenceInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			for (int i = 0; i < src.length; i++) {
				v.add(new FileInputStream(new File(src[i])));
			}
			Enumeration<? extends InputStream> en = v.elements();
			sequenceInputStream = new SequenceInputStream(en);
			// 使用FileOutputStream,文件会自动建立
			fileOutputStream = new FileOutputStream(new File(newPath));

			int length = 0;
			byte[] ch = new byte[1024];
			while ((length = sequenceInputStream.read(ch)) != -1) {
				fileOutputStream.write(ch, 0, length);
			}

		} catch (FileNotFoundException e) {
			AllException.handle(e);
		} catch (IOException e) {
			AllException.handle(e);
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {

				try {
					if (sequenceInputStream != null)
						sequenceInputStream.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * 切割文件 对于视频文件分割用 ffmpeg ;不然分割文件无效,只有第一部分可以播放
	 * 
	 * @param src    源文件
	 * @param outDir 输出目录
	 * @param size   每个文件大小
	 */
	public static void spitFile(String src, String outDir, Size size) {
		if (src == null) {
			return;
		}
		int length = 0;
		byte[] ch = new byte[1024 * 1024 * size.length];
		FileInputStream fileInputStream = null;

		try {
			String suffix = src.substring(src.lastIndexOf("."));
			int index = 0;
			fileInputStream = new FileInputStream(new File(src));

			while ((length = fileInputStream.read(ch)) != -1) {

				// 创建文件
				++index;
				createNewFile(outDir, "part" + (index) + suffix);
				// 赋值
				FileOutputStream fileOutputStream = new FileOutputStream(new File(outDir, "part" + index + suffix));
				// 写内容
				fileOutputStream.write(ch, 0, length);
				// 关流
				fileOutputStream.close();
			}
		} catch (FileNotFoundException e) {
			AllException.handle(e);
		} catch (IOException e) {
			AllException.handle(e);
		} finally {
			closeIO(fileInputStream);
		}

	}

	/**
	 * 把文件写到硬盘上
	 * 
	 * @param user
	 */
	public static void obj2File(User user, String fileName) {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
			outputStream.writeObject(user);
		} catch (FileNotFoundException e) {
			AllException.handle(e);
		} catch (IOException e) {
			AllException.handle(e);
		} finally {
			closeIO(outputStream);
		}
	}

	/**
	 * 读取文件对象数据
	 * 
	 * @param fileName
	 */
	public static Object readObjFile(String fileName) {
		ObjectInputStream objectInputStream = null;
		Object object = null;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(new File(fileName)));
			object = objectInputStream.readObject();
		} catch (FileNotFoundException e) {
			AllException.handle(e);
		} catch (IOException e) {
			AllException.handle(e);
		} catch (ClassNotFoundException e) {
			AllException.handle(e);
		} finally {
			closeIO(objectInputStream);
		}
		return object;
	}

	public static void main(String[] args) {

		
	}

}
