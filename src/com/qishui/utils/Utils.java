package com.qishui.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class Utils {

	public static void out(Object... obj) {

		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		if (obj == null) {
			System.out.print("");
		} else {
			for (int i = 0; i < obj.length; i++) {
				sb.append(obj[i]).append(",");
			}
		}
		sb.append(" ]").append("\n");
		System.out.print(sb.toString());

	}
	
	public static void outCollection(Collection<Object> coll) {
		
		Iterator<Object> it=coll.iterator();
		while (it.hasNext()) {
			out(it.next());
		}
		
	}

	public static void getDate() {

		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ww====zz==FF==EE ");
		out(sm.format(date));

// 将字符串转换成Date
//		try {
//			sm.parse("");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		out(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));

	}

}
