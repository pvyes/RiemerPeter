package main;

import controller.ErrorInteraction;
import controller.UIFactory;

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
		ErrorInteraction ei = (ErrorInteraction) UIFactory.createUserInteraction(UIFactory.ERROR);
		ei.setTitle(messageTitle);
		ei.setMessage(message);
		ei.show();
	}
}