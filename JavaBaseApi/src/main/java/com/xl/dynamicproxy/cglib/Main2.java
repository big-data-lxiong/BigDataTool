package com.xl.dynamicproxy.cglib;

import com.xl.dynamicproxy.jdk.service.UserService;
import com.xl.dynamicproxy.jdk.service.impl.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;

public class Main2 {

	public static void main(String[] args) {

		CglibProxy cglibProxy = new CglibProxy();

		Enhancer enhancer = new Enhancer(); //主要的增强类
		enhancer.setSuperclass(UserServiceImpl.class); //设置父类，被增强的类
		enhancer.setCallback(cglibProxy); //回调对象

		UserService o = (UserService)enhancer.create();//用cglibProxy来增强UserServiceImpl
		o.getName(1);
		o.getAge(1);
	}

}
