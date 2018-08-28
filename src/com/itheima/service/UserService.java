package com.itheima.service;

import java.sql.SQLException;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;



public class UserService {

	public boolean regist(User user) {
		UserDao dao=new UserDao();
		int row=0;
		try {
			row = dao.regist(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row>0?true:false;
	}
	//激活
	public void active(String activeCode) {
		UserDao dao = new UserDao();
		try {
			dao.active(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//校验用户名是否存在
	public boolean checkUsername(String username) {
		UserDao dao= new UserDao();
		Long isExist=0L;
		try {
			isExist=(Long)dao.checkUsername(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isExist>0?true:false;//存在true，不存在false
	}

}
