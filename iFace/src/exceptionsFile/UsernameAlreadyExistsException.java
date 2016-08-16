package exceptionsFile;

public class UsernameAlreadyExistsException extends Exception {
	public UsernameAlreadyExistsException() {
		super();
	} 
	  public UsernameAlreadyExistsException(String message) { super(message); }
}	
