package iFace;

import java.util.ArrayList;
import java.util.Random;

public class Community {
	protected String name;
	protected String info;
	protected String owner;	
	protected Integer id;
	protected ArrayList<User> users = new ArrayList<User>();
	
	public String setName(String name){		
		this.name = name;
		return name;
	}
	
	public String setInfo(String info){		
		this.info = info;
		return info;
	}
	
	public String setOwner(String owner){		
		this.owner = owner;
		return owner;
	}
	public boolean addUser(Integer newUser) {
		return users.add(newUser); 
	}

	public Integer setId(Integer id){
		Random generator = new Random();
		this.id = generator.nextInt(8999999) + 201000000;
		Management.getInstanceOf().addCommunity(this);
		this.id = id;
		return id;
	}
}
