//package com.example.demo.Controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.Model.UserModel;
//import com.example.demo.Service.UserService;
//
//@CrossOrigin("http://localhost:5173/")
//@RestController
//public class UserController 
//{
//	@Autowired
//	UserService userservice;
//	
//	@PostMapping("/addUser")
//	public String AddUser(@RequestBody UserModel usermodel)
//	{
//		boolean b=userservice.isAddNewUser(usermodel);
//		
//		if(b)
//		{
//			return "New User Added Successfully.....!";
//		}
//		else
//		{
//			return "New User NOT  Added Successfully.....!";
//		}
//	}
//	
//	
//	@GetMapping("/getAllUsers")
//	public List<UserModel> getAllUsers()
//	{
//		return userservice.getAllUsers();
//	}
//	
//	
//	@GetMapping("/searchUser/{uid}")
//	public UserModel getUserById(@PathVariable("uid") Integer uid)
//	{
//		UserModel umodel=userservice.getUserById(uid);
//		if(umodel!=null)
//		{
//			return umodel;
//		}
//		else
//		{
//			return null;
//		}
//	}
//	
//
//	@PostMapping("/loginUser")
//	public String getUserByEmailPass(@RequestBody UserModel user) {
//		
//	    boolean b = userservice.getUserByEmailPass(user.getEmail(), user.getPassword());
//	    
//	    if (b) {
//	        return "You Are logged In Successfully...!";
//	    } else {
//	        return "You Are NOT logged In Successfully...!";
//	    }
//	}
//
//	@PostMapping("/customerLogin")
//	public String getCustomerByEmailPass(@RequestBody UserModel user) {
//		
//	    boolean b = userservice.getCustomerByEmailPass(user.getEmail(), user.getPassword());
//	    
//	    if (b) 
//	    {
//	        return "You Are logged In Successfully in Customer Panel...!";
//	    } 
//	    else 
//	    {
//	        return "You Are NOT logged In Successfully...!";
//	    }
//	}
//
//	
//	
//	@DeleteMapping("/deleteUser/{uid}")
//	public String deleteUserById(@PathVariable("uid") Integer uid)
//	{
//		boolean  b=userservice.isDeleteUserById(uid);
//		
//		if(b)
//		{
//			return "User Deleted Successfully....!";
//		}
//		else
//		{
//			return "User NOT Deleted Successfully....!";
//		}
//		
//	}
//	
//	
//	@PutMapping("/updateEmployee")
//	public String updateUser(@RequestBody UserModel umodel)
//	{
//		boolean b=userservice.isupdateUser(umodel);
//		
//		if(b)
//		{
//			return "User Updated Successfully.....!";
//		}
//		else
//		{
//			return "User NOT Updated Successfully.....!";
//		}
//		
//	}
//	
//	
//	@GetMapping("/getUserPattern/{upattern}")
//	public List<UserModel>  getUserPattern(@PathVariable("upattern") String upattern)
//	{
//		List<UserModel> uList=userservice.getUserPattern(upattern);
//		
//		if(uList.size()>0)
//		{
//			return uList;
//		}
//		else
//		{
//			return null;
//		}
//	}
//	
//}

package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.AppointmentModel;
import com.example.demo.Model.UserModel;
import com.example.demo.Service.UserService;


@CrossOrigin("http://localhost:5173/")
@RestController
public class UserController 
{
	@Autowired
	UserService userservice;
	
	@Autowired
    JavaMailSender javaMailSender;
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody UserModel usermodel) {
	    boolean isAdded = userservice.isAddNewUser(usermodel);

	    if (isAdded)
	    {
	        String email = usermodel.getEmail();
	        if (email != null && !email.isEmpty()) {
	            SimpleMailMessage message = new SimpleMailMessage();
	            message.setTo(email);
	            message.setSubject("Registration Confirmation");
	            message.setText("Dear " + usermodel.getUname() +
	                ",\n\nCongratulations! You are registered successfully." +
	                "\n\nUsername: " + usermodel.getEmail() +
	                "\n\nPassword :" +usermodel.getPassword()+
	                "\n\nUser ID: " + usermodel.getUid() +
	                "\n\nThank you!" +
	                "\n\nRegards," +
	                "\nRMOTORS Garage Services" +
	                "\nContact: 8055967453" +
	                "\nEmail: rmotorsgarageservices2001@gmail.com");

	            javaMailSender.send(message);
	        }

	        return ResponseEntity.status(HttpStatus.CREATED).body("New user added and confirmation email sent.");
	    } 
	    else 
	    {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.");
	    }
	}

	
	
	@GetMapping("/getAllUsers")
	public List<UserModel> getAllUsers()
	{
		return userservice.getAllUsers();
	}
	
	
	@GetMapping("/searchUser/{uid}")
	public UserModel getUserById(@PathVariable("uid") Integer uid)
	{
		UserModel umodel=userservice.getUserById(uid);
		if(umodel!=null)
		{
			return umodel;
		}
		else
		{
			return null;
		}
	}
	

	@PostMapping("/loginUser")
	public String getUserByEmailPass(@RequestBody UserModel user) {
		
	    boolean b = userservice.getUserByEmailPass(user.getEmail(), user.getPassword());
	    
	    if (b) {
	        return "You Are logged In Successfully...!";
	    } else {
	        return "You Are NOT logged In Successfully...!";
	    }
	}

	@PostMapping("/customerLogin")
	public String getCustomerByEmailPass(@RequestBody UserModel user) {
		
	    boolean b = userservice.getCustomerByEmailPass(user.getEmail(), user.getPassword());
	    
	    if (b) 
	    {
	        return "You Are logged In Successfully in Customer Panel...!";
	    } 
	    else 
	    {
	        return "You Are NOT logged In Successfully...!";
	    }
	}

	
	
	@DeleteMapping("/deleteUser/{uid}")
	public String deleteUserById(@PathVariable("uid") Integer uid)
	{
		boolean  b=userservice.isDeleteUserById(uid);
		
		if(b)
		{
			return "User Deleted Successfully....!";
		}
		else
		{
			return "User NOT Deleted Successfully....!";
		}
		
	}
	
	
	@PutMapping("/updateEmployee")
	public String updateUser(@RequestBody UserModel umodel)
	{
		boolean b=userservice.isupdateUser(umodel);
		
		if(b)
		{
			return "User Updated Successfully.....!";
		}
		else
		{
			return "User NOT Updated Successfully.....!";
		}
		
	}
	
	
	@GetMapping("/getUserPattern/{upattern}")
	public List<UserModel>  getUserPattern(@PathVariable("upattern") String upattern)
	{
		List<UserModel> uList=userservice.getUserPattern(upattern);
		
		if(uList.size()>0)
		{
			return uList;
		}
		else
		{
			return null;
		}
	}
	
}

