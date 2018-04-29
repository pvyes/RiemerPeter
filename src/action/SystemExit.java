package action;

/**
 * @author peter
 *
 */
public class SystemExit implements Action {
	private String key;
	
	protected SystemExit() {
		key = ActionFactory.SYSTEM_EXIT;
	}
	
	public void performAction() {
		System.exit(0);		
	}

	public String getKey() {
		return key;
	}
	
	

}
