package iFace;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {
	@Id
	@GeneratedValue
	protected int id;
	protected String content;

	@ManyToOne
	protected User userSender;

	@ManyToOne
	protected User userReciever;

	public Message() {

	}
	
	public Message(int id, String content, User userSender, User userReciever) {
		super();
		this.id = id;
		this.content = content;
		this.userSender = userSender;
		this.userReciever = userReciever;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUserSender() {
		return userSender;
	}

	public void setUserSender(User userSender) {
		this.userSender = userSender;
	}

	public User getUserReciever() {
		return userReciever;
	}

	public void setUserReciever(User userReciever) {
		this.userReciever = userReciever;
	}

}
