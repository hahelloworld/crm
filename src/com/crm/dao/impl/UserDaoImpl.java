package com.crm.dao.impl;


import java.util.List;


import com.crm.dao.UserDao;
import com.crm.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void regist(User user) {
		this.getHibernateTemplate().save(user);
		
	}

	@Override
	public User login(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?", user.getUser_code(),user.getUser_password());
		if(list.size()>0)
			return list.get(0);
		return null;
	}

}
