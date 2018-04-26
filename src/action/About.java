package action;

import controller.UserInteraction;
import main.JabberPoint;

/**
 * @author peter
 *
 */
public class About implements Action {
	private String key;
	
	protected About() {
		key = ActionFactory.ABOUT;
	}

	public void performAction() {
	     new UserInteraction(UserInteraction.MESSAGE, "About", JabberPoint.ABOUT);
	}

	public String getKey() {
		return key;
	}
}
