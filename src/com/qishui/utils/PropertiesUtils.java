package com.qishui.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.qishui.exception.AllException;

/**
 * 属性类
 * 
 * @author zhou
 *
 */
public class PropertiesUtils {

	/**
	 * 
	 * @param propertiesDir 配置目录
	 * @param hashMap       配置key-value
	 * @param desc          配置描述
	 */
	public static void creatProperties(String propertiesDir, HashMap<String, String> hashMap, String desc) {

		if (hashMap == null) {
			return;
		}
		// 对象
		Properties p = new Properties();
		Set<Map.Entry<String, String>> set = hashMap.entrySet();

		for (Map.Entry<String, String> entry : set) {
			p.setProperty(entry.getKey(), entry.getValue());
		}

		try {
			// 可以出现中文
			p.store(new FileWriter(new File(propertiesDir)), desc);
			// 8859-1
			// p.store(new FileOutputStream(new File("E://setinfo.properties")), "描述信息");
		} catch (IOException e) {
			AllException.handle(e);
		}

	}

	/**
	 * 加载读取属性文件
	 * 
	 * @param propertiesDir
	 */
	public static HashMap<String, String> readProperties(String propertiesDir) {
		Properties p = new Properties();
		HashMap<String, String> hashMap = new HashMap<>();
		try {
			// 加载信息到Properties p中
			p.load(new FileReader(new File(propertiesDir)));

			Set<Entry<Object, Object>> set = p.entrySet();

			for (Map.Entry<Object, Object> entry : set) {
				hashMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
			}

		} catch (IOException e) {
			AllException.handle(e);
		}
		return hashMap;
	}

	public static void main(String[] args) {
//		HashMap<String, String> hashMap = new HashMap<>();
//		hashMap.put("key1", "10");
//		hashMap.put("key2", "102");
//		hashMap.put("key3", "10001");
//		hashMap.put("中文开关", "关");
//		hashMap.put("中文开关333", "11关");
//		creatProperties("E://setinfo.properties", hashMap, "xxxx");
//		
		readProperties("E://setinfo.properties");
	}

}
