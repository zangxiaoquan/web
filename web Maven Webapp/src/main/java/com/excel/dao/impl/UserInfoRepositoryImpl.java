package com.excel.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.excel.dao.UserInfoRepository;
import com.excel.entity.UserInfo;

public class UserInfoRepositoryImpl implements UserInfoRepository{
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }
    
	@Override
	public UserInfo load(Long id) {
		return (UserInfo)getCurrentSession().load(UserInfo.class,id);
	}

	@Override
	public UserInfo get(Long id) {
		return (UserInfo)getCurrentSession().get(UserInfo.class,id);
	}

	@Override
	public List<UserInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(UserInfo entity) {
		getCurrentSession().persist(entity);
	}

	@Override
	public Long save(UserInfo entity) {
		return (Long)getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(UserInfo entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Long id) {
		UserInfo userInfo = load(id);
        getCurrentSession().delete(userInfo);
	}

	@Override
	public void flush() {
		getCurrentSession().flush();
	}

}
