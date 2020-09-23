package com.qishui.utils;

import java.security.MessageDigest;

/**
 * MD5加密处理
 * 
 * @author zhou
 *
 */
public class MD5Util {

	// 盐，用于混交md5
	private static final String SLAT = "...!UU3333###%%%%%...";

	public static String encrypt(String dataStr) {

		StringBuffer stringBuffer = new StringBuffer();
		try {
			dataStr = dataStr + SLAT;
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(dataStr.getBytes("UTF-8"));
			byte s[] = m.digest();

			for (int i = 0; i < s.length; i++) {
				stringBuffer.append(Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6));
			}
			return stringBuffer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}
