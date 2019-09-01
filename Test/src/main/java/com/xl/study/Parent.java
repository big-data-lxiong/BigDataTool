package com.xl.study;

public class Parent {

	public Parent(){
		System.out.println("com.xl.study.Parent 普通代码块");
	}

	{
		System.out.println("com.xl.study.Parent 构造代码块");
	}

	static{
		System.out.println("com.xl.study.Parent 静态代码块");
	}

}
