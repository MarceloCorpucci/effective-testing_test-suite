package com.effectivetesting.helper;

import com.effectivetesting.entity.User;

public class UserHelper {
	public static User define(String id, 
						 	  String email, 
							  String password, 
							  String name) {
		User user = new User();
		
		user.setId(id);
		user.setEmail(email);
		user.setpassword_hash(password);
		user.setName(name);
		
		return user;
	}

}
