package com.qishui.clone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.qishui.exception.AllException;
import com.qishui.file.FileUtils;

/**
 * 对象克隆
 * 
 * @author zhou
 *
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			// 浅克隆,内存地址相同
			User u1 = new User("u1", 26);
			User u2 = (User) u1.clone();
			u2.setName("zhou");
			// 深克隆 两个对象,内存地址不同
			FileUtils.obj2File(u1, "C:\\Users\\zhou\\Desktop\\temp.txt");
			User u3 = (User) FileUtils.readObjFile("C:\\Users\\zhou\\Desktop\\temp.txt");
			
			System.out.println(u1);
			System.out.println(u2);
			System.out.println(u3);

		} catch (CloneNotSupportedException e) {
			AllException.handle(e);
		}

	}

	

}
