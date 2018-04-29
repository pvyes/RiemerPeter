package action;

import controller.MessageInteraction;
import controller.UIFactory;
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
		MessageInteraction mi = (MessageInteraction) UIFactory.createUserInteraction(UIFactory.MESSAGE);
		mi.setTitle("About");
		mi.setMessage(JabberPoint.ABOUT);
	    mi.show();
	}

	public String getKey() {
		return key;
	}
}
