package com.qishui.model.decorator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.qishui.model.decorator.demo1.Father;
import com.qishui.model.decorator.demo1.Mather;
import com.qishui.model.decorator.demo1.Son;
import com.qishui.model.decorator.demo2.BufferLineNum;
import com.qishui.model.decorator.demo2.BufferLineQuto;
import com.qishui.model.decorator.demo2.BufferLineSemi;

/**
 * 装饰者模式
 * @author zhou
 *
 */
public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		new Father(new Mather(new Son())).work();

		String line;
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				new File("E:\\Java_Ec\\workspace\\QiShuiDemo\\src\\com\\qishui\\HttpRequestUtils.java")));

		BufferLineNum bufferLineNum = new BufferLineNum(bufferedReader);
		// 行号+分号
		BufferLineSemi bufferLineSemi = new BufferLineSemi(bufferLineNum);
		// 所有功能
		BufferLineQuto bufferLineQuto = new BufferLineQuto(bufferLineSemi);

		while ((line = bufferLineQuto.readLine()) != null) {
			System.out.println(line);
		}
		bufferLineQuto.close();

	}

}
