package com.qishui.clone;

import java.io.Serializable;

/**
 * 对象序列化
 * 
 * @author zhou
 *
 */
public class User implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	String name;

	int age;

	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
