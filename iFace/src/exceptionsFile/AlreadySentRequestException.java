package exceptionsFile;

public class AlreadySentRequestException extends Exception {
	public AlreadySentRequestException() {
		super();
	} 
	  public AlreadySentRequestException(String message) { super(message); }
}
