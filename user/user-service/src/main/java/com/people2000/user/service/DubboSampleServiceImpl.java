package com.people2000.user.service;

import org.springframework.stereotype.Service;

import com.people2000.user.interfaces.DubboSampleService;

@Service("dubboSampleService")
public class DubboSampleServiceImpl implements DubboSampleService {

	@Override
	public int test() {
		return 1;
	}

}
