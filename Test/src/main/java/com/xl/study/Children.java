package com.xl.study;

public class Children extends Parent {

	public Children(){
		System.out.println("com.xl.study.Children 普通代码块");
	}

	{
		System.out.println("com.xl.study.Children 构造代码块");
	}

	static{
		System.out.println("com.xl.study.Children 静态代码块");
	}

}
