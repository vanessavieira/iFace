package iFace;

import java.util.ArrayList;
import java.util.Random;

public class Community {
	protected String name;
	protected String info;
	protected int owner;
	protected Integer id;
	protected ArrayList<User> users = new ArrayList<User>();

	public Community(String name, String info, int owner) {
		this.name = name;
		this.info = info;
		this.owner = owner;
		Random generator = new Random();
		this.id = generator.nextInt(8999999) + 101000000;
		Management.getInstanceOf().addCommunity(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public boolean addUser(Integer newUser) {
		return users.add(newUser);
	}

	public boolean containsUser(int uId) {
		for (int i = 0; i < users.size(); i++)
			if (users.get(i) == uId)
				return true;
		return false;
	}

	public User getUserById(int uId) {
		User u = null;
		for (int i = 0; i < users.size(); i++)
			if (users.get(i) == uId)
				u = Management.getInstanceOf().getUserById(users.get(i));
		return u;
	}

	public void print() {
		System.out.println("NAME:" + this.name);
		System.out.println("INFO:" + this.info);
		System.out.println("OWNER:" + this.owner);
		System.out.println("ID:" + this.id);
		System.out.println(users.size() > 0 ? "USERS:" : "NO USERS");
		for (int i = 0; i < users.size(); i++)
			System.out.println(Management.getInstanceOf()
					.getUserById(users.get(i)).getName());
	}

	public boolean removeUser(int uId) {
		if (uId == owner)
			return Management.getInstanceOf().removeCommunity(this.id);
		User u = getUserById(uId);
		if (u == null) {
			System.out.println("User Not Found " + this.name);
			return false;
		}
		return users.remove(u.getId());
	}

	public boolean kill() {
		for (int i = 0; i < users.size(); i++) {
			User u = Management.getInstanceOf().getUserById(users.get(i));
			if (!u.removeCommunity(this.id))
				return false;
		}
		User u = Management.getInstanceOf().getUserById(owner);
		if (!u.removeCommunity(this.id))
			return false;
		return true;
	}
}
