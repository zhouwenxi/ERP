package com.qishui.basic;

import java.util.ArrayList;
import java.util.Scanner;

/***
 * Java登录注册查询简单操作系统
 * 
 * @author zhou 2020年9月17日10:43:00
 */
public class LoginAndReg {

	// 学生类
	class Student {

		String name;
		String id;

		public Student(String id, String name) {

			this.name = name;
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "学生ID:" + this.id + "学生姓名:" + this.name;
		}
	}

	/**
	 * 登录注册查询功能练习
	 */
	public static void toLogAndReg() {

		ArrayList<LoginAndReg.Student> list = new ArrayList<LoginAndReg.Student>();

		while (true) {
			System.out.println("请选择登录A或者注册B或者查询C。");

			Scanner scanner = new Scanner(System.in);
			String value = scanner.next();
			// 信息保存集合
			// 选择登录或者注册
			if ("a".equalsIgnoreCase(value)) {
				// 登录
				while (true) {

					System.out.println("输入学生ID:");
					String id = scanner.next();
					Boolean flag = false;
					for (int i = 0; i < list.size(); i++) {
						if (id.equals(list.get(i).getId())) {
							System.out.println("登录成功," + list.get(i));
							flag = true;
						}
					}

					if (flag == false) {
						System.out.println("学生ID不存在。");
					}
					break;
				}

			} else if ("b".equalsIgnoreCase(value)) {

				Out: while (true) {
					// 注册
					System.out.println("输入学生ID:");
					String id = scanner.next();
					Boolean flag = true;
					for (int i = 0; i < list.size(); i++) {
						if (id.equals(list.get(i).getId())) {
							System.out.println("学生ID已存在。");
							flag = false;
							break Out;
						}
					}

					// 添加数据
					if (flag) {
						System.out.println("输入学生姓名:");
						String name = scanner.next();
						LoginAndReg.Student student = new LoginAndReg().new Student(id, name);
						list.add(student);

						System.out.println("注册成功," + student);
						break;
					}
				}

			} else if ("c".equalsIgnoreCase(value)) {

				while (true) {
					if (list.size() == 0) {
						System.out.println("当前学生信息为空。");
					} else {
						System.out.println("当前学生信息:");
						for (int i = 0; i < list.size(); i++) {
							System.out.println(list.get(i));
						}
					}
					break;
				}

			} else {
				System.out.println("输入错误。");
			}

		}

	}

	public static void main(String[] args) {

		toLogAndReg();

	}

}
