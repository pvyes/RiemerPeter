package main;

import controller.UserInteraction;

/**
 * @author peter
 *
 */
public class JabberPointException extends Exception {
 
	public JabberPointException(Exception e, String messageTitle, String friendlyMessage) {
		super();
		String errormessage = "";
		if (e != null) {
			errormessage = e.getMessage();
		}
		String message = friendlyMessage +  "\n\n" + errormessage;
		new UserInteraction(UserInteraction.ERROR, messageTitle, message);
	}
}
