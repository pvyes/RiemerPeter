package controller;

import javax.swing.JOptionPane;

/**
 * @author peter
 *
 */
public class UserInteraction {
	public static final String MESSAGE = "message";
	public static final String INPUT = "input";
	public static final String ERROR = "error";
	
	private String kind;
	private String title;
	private String message;
	private String defaultValue = "";
	private String answer = "";

	public UserInteraction(String interactionKind, String title, String message) {
		kind = interactionKind;
		this.title = title;
		this.message = message;
		show();
	}

	public UserInteraction(String interactionKind, String title, String message, String defaultValue) {
		kind = interactionKind;
		this.title = title;
		this.message = message;
		this.defaultValue = defaultValue;
		show();
	}

	private void show() {
		switch (kind) {
			case MESSAGE :
				JOptionPane.showMessageDialog(null,
						message, title,
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case ERROR :
				JOptionPane.showMessageDialog(null,
						message, title,
						JOptionPane.ERROR_MESSAGE);
				break;
			case INPUT :
				answer = (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE, null, null, defaultValue);
				break;		
		}		
	}
	
	public String getAnswer() {
		return answer;
	}
	
	
}
