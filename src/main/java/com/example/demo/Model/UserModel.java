package com.example.demo.Model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Component("user")
public class UserModel 
{
	private int uid;
	private String uname;
	private String email;
	private String contact;
	private String address;
	private String password;
	private String role;

}
