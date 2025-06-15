package com.example.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.example.demo.Model.UserModel;

@Repository("urepo")
public class UserRepository
{
	@Autowired
	JdbcTemplate template;
	
	List<UserModel> ulist;
	
	

	public boolean isAddNewUser(UserModel usermodel)
	{
	    KeyHolder keyHolder = new GeneratedKeyHolder();

	    int value = template.update(connection -> {
	        PreparedStatement ps = connection.prepareStatement("INSERT INTO usermodel (uname, email, contact, address, password, role) VALUES (?, ?, ?, ?, ?, ?)",new String[] { "uid" } );
	        ps.setString(1, usermodel.getUname());
	        ps.setString(2, usermodel.getEmail());
	        ps.setString(3, usermodel.getContact());
	        ps.setString(4, usermodel.getAddress());
	        ps.setString(5, usermodel.getPassword());
	        ps.setString(6, "Customer");
	        return ps;
	    }, keyHolder);

	    if (value > 0) 
	    {
	        Number key = keyHolder.getKey();
	        if (key != null) 
	        {
	            usermodel.setUid(key.intValue());
	        }
	        return true;
	    } 
	    else 
	    {
	        return false;
	    }
	}
	
	public List<UserModel> getAllUsers()
	{
		ulist=template.query("select * from usermodel order by uname asc",new RowMapper<UserModel>() 
		{
			public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				UserModel umodel=new UserModel();
				
				umodel.setUid(rs.getInt(1));
				umodel.setUname(rs.getString(2));
				umodel.setEmail(rs.getString(3));
				umodel.setContact(rs.getString(4));
				umodel.setAddress(rs.getString(5));
				umodel.setPassword(rs.getString(6));
				umodel.setRole(rs.getString(7));
				
				return umodel;
			}	
		});
		return ulist;
	}
	
	public UserModel getUserById( int uid)
	{
		ulist=template.query("select *from Usermodel where uid=?", new Object[] {uid}, new RowMapper<UserModel> () {

			public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException
			{
                UserModel umodel=new UserModel();
				
				umodel.setUid(rs.getInt(1));
				umodel.setUname(rs.getString(2));
				umodel.setEmail(rs.getString(3));
				umodel.setContact(rs.getString(4));
				umodel.setAddress(rs.getString(5));
				umodel.setPassword(rs.getString(6));
				umodel.setRole(rs.getString(7));
				return umodel;
			}
		});
		
		return ulist.size()>0?(UserModel)ulist.get(0):null;
	}
	

	public boolean getUserByEmailPass(String email, String password)
	{
		 List<UserModel> ulist = template.query("select * from Usermodel where email=? and password=? and role='admin' ", new Object[]{email, password},(rs, rowNum) -> {
			            UserModel umodel = new UserModel();
			            
			            umodel.setEmail(rs.getString("email"));
			            umodel.setPassword(rs.getString("password"));
			            return umodel;
			        }
			    );
		
		return ulist.size()>0?true:false;
		
	}
	
	
	public boolean getCustomerByEmailPass(String email, String password)
	{
		 List<UserModel> ulist = template.query("select * from Usermodel where email=? and password=? and role='customer' ", new Object[]{email, password},(rs, rowNum) -> {
			            UserModel umodel = new UserModel();
			            
			            umodel.setEmail(rs.getString("email"));
			            umodel.setPassword(rs.getString("password"));
			            return umodel;
			        }
			    );
		
		return ulist.size()>0?true:false;
		
	}
	
	public boolean isDeleteUserById(int uid)
	{
		int value=template.update("delete from usermodel where uid="+uid);
		if(value>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public boolean isupdateUser(UserModel umodel)
	{
		int value=template.update("update usermodel set uname=?, email=?,contact=?,address=?,password=? where uid=?",new PreparedStatementSetter () {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, umodel.getUname());
				ps.setString(2, umodel.getEmail());
				ps.setString(3, umodel.getContact());
				ps.setString(4, umodel.getAddress());
				ps.setString(5, umodel.getPassword());
				ps.setInt(6,umodel.getUid());	
			}
		});
		if(value >0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public List<UserModel>  getUserPattern(String upattern)
	{
		List<UserModel> uList=template.query("select *from usermodel where uname like '%"+upattern+"%' ", new RowMapper<UserModel> () {

			public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				UserModel umodel=new UserModel();
				umodel.setUid(rs.getInt(1));
				umodel.setUname(rs.getString(2));
				umodel.setEmail(rs.getString(3));
				umodel.setContact(rs.getString(4));
				umodel.setAddress(rs.getString(5));
				umodel.setPassword(rs.getString(6));
				umodel.setRole(rs.getString(7));
				return umodel;
			}
			
		});
		return uList;
	}

}
