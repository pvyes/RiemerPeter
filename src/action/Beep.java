package action;

import java.awt.Toolkit;

/**
 * @author peter
 *
 */
public class Beep implements Action {
	private String key;
	
	protected Beep() {
		key = ActionFactory.BEEP;
	}

	public void performAction() {
	     Toolkit.getDefaultToolkit().beep();	
	}

	public String getKey() {
		return key;
	}
}
