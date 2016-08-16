package exceptionsFile;

public class CantAddYourselfException extends Exception {
	public CantAddYourselfException() {
		super();
	} 
	  public CantAddYourselfException(String message) { super(message); }
}
