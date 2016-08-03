package iFace;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserFriendship {
	@Id
	@GeneratedValue
	int userFriendshipId;
	User user1;
	User user2;
	boolean isFriend = false;
}
