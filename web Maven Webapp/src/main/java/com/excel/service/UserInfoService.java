package com.excel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.excel.dao.UserInfoRepository;
import com.excel.entity.UserInfo;

@Service
public class UserInfoService {
	@Autowired
    private UserInfoRepository UserInfoRepository;
	
	public Long saveUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId("liulz");
		userInfo.setPassword("111111");
        return UserInfoRepository.save(userInfo);
    }
}
