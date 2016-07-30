package iFace;

import java.util.ArrayList;
import java.util.Random;

public class User {
	protected String name;
	protected String login;
	protected String email;
	protected String password;	
	protected Integer id;
	protected ArrayList<String> messages = new ArrayList<String>();
	protected ArrayList<Integer> communities = new ArrayList<Integer>();
	protected ArrayList<User> friends = new ArrayList<User>();
	
	public String setName(String name){		
		this.name = name;
		return name;
	}
	
	public String setLogin(String login){		
		this.login = login;
		return login;
	}
	
	public String setEmail(String email){		
		this.email = email;
		return email;
	}
	
	public String setPassword(String password){		
		this.password = password;
		return password;
	}
	
	public Integer setId(Integer id){
		Random generator = new Random();
		this.id = generator.nextInt(8999999) + 201000000;
		Management.getInstanceOf().addUser(this);
		this.id = id;
		return id;
	}
		
}
