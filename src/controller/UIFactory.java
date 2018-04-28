package controller;

/**
 * @author peter
 *
 */
public class UIFactory {
	public static final String UIF_ERROR = "User Interaction creation error";
	public static final String UIF_ERROR_MESSAGE = "De gevraagde UserInteraction is niet gekend.";
	
	public static final String INPUT = "input";
	public static final String MESSAGE = "message";
	public static final String ERROR = "error";
	
	public static UserInteraction createUserInteraction(String kind) {
		switch (kind) {
			case INPUT: return new InputInteraction();
			case MESSAGE: return new MessageInteraction();
			case ERROR: return new ErrorInteraction();
			default: {
				ErrorInteraction ei = new ErrorInteraction();
				ei.setTitle(UIF_ERROR);
				ei.setMessage(UIF_ERROR_MESSAGE);
				ei.show();
				return null;
			}
		}
	}
}
	
