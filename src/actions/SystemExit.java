package actions;

/**
 * @author peter
 *
 */
public class SystemExit implements Action {
	private String key;
	
	protected SystemExit() {
	}
	
	public void performAction() {
		System.exit(0);		
	}

	public String getKey() {
		return key;
	}
	
	

}
