package com.people.cbsadmin.service;

import org.springframework.stereotype.Service;

import com.people.cbsadmin.interfaces.DubboSampleService;

@Service("dubboSampleService")
public class DubboSampleServiceImpl implements DubboSampleService {

	@Override
	public int test() {
		return 1;
	}

}
