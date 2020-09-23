package com.qishui.model.decorator.demo2;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 添加行号
 * 
 * @author zhou
 *
 */
public class BufferLineNum extends BufferedReader {

	public BufferedReader bufferedReader;
	public int index = 0;

	public BufferLineNum(BufferedReader bufferedReader) {
		super(bufferedReader);
		this.bufferedReader = bufferedReader;

	}

	@Override
	public String readLine() throws IOException {

		String line = bufferedReader.readLine();
		if (line == null) {
			return null;
		}
		line = (++index) + line;
		return line;
	}

}
