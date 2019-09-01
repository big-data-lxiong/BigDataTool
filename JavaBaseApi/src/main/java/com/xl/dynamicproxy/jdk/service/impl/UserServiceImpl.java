package com.xl.dynamicproxy.jdk.service.impl;

import com.xl.dynamicproxy.jdk.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public String getName(int id) {
		System.out.println("------getName------");
		return "hello";
	}

	@Override
	public Integer getAge(int id) {
		System.out.println("------getAge------");
		return 10;
	}

}
