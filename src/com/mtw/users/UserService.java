package com.mtw.users;

import java.sql.SQLException;

import com.mtw.common.ResultCode;

public class UserService {
	
	private UserDao userDao =new UserDao();
	public ResultCode checkLogin(User user) {
	try {
		int count = userDao.selectCountByUsernameAndPassword(user);
		if(count==1)
			return ResultCode.SUCCESS;
		else 
			return ResultCode.ERROR_USER;
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return ResultCode.ERROR_SYSTEM;
	}
}

	public ResultCode changePassword(User user) {
		try {
			userDao.updatePassword(user);
			return ResultCode.SUCCESS;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultCode.ERROR_SYSTEM;
		}
	}
}
