package com.xl.dynamicproxy.jdk;

import com.xl.dynamicproxy.jdk.service.UserService;
import com.xl.dynamicproxy.jdk.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JavaProxy {

	public static void main(String[] args){
		UserService userService = new UserServiceImpl();
		InvocationHandler invocationHandler = new MyInvocationHandler(userService);

		UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
			userService.getClass().getInterfaces(), invocationHandler);

		System.out.println(userServiceProxy.getName(1));
		System.out.println(userServiceProxy.getAge(1));
	}

}
