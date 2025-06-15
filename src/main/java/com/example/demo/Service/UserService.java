package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.UserModel;
import com.example.demo.Repository.UserRepository;

@Service("userservice")
public class UserService
{
	@Autowired
	UserRepository urepo;
	
	public boolean isAddNewUser(UserModel usermodel)
	{
		return urepo.isAddNewUser(usermodel);
	}
	
	public List<UserModel> getAllUsers()
	{
		return urepo.getAllUsers();
	}
	
	public UserModel getUserById( int uid)
	{
		return urepo.getUserById(uid);
	}
	
	public boolean isDeleteUserById(int uid)
	{
		return urepo.isDeleteUserById(uid);
	}

	public boolean isupdateUser(UserModel umodel)
	
	{
		return urepo.isupdateUser(umodel);
	}
	
	public boolean getUserByEmailPass(String email, String password)
	{
		return urepo.getUserByEmailPass(email, password);
	}
	
	public boolean getCustomerByEmailPass(String email, String password)
	{
		return urepo.getCustomerByEmailPass(email, password);
	}
	
	public List<UserModel>  getUserPattern(String upattern)
	{
		return urepo.getUserPattern(upattern);
	}

}
