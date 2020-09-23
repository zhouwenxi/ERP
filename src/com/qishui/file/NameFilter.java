package com.qishui.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 文件名字过滤器
 * 
 * @author zhou
 *
 */
public class NameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		return name.endsWith(".avi");
	}

}
