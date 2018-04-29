package controller;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * @author peter
 *
 */
public class InputInteraction implements UserInteraction {
	public static final String MESSAGE = "message";
	public static final String INPUT = "input";
	public static final String ERROR = "error";
	
	private String title;
	private String message;
	private String defaultValue = "";
	private String answer = "";
	private JComponent view;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public JComponent getView() {
		return view;
	}

	public void setView(JComponent view) {
		this.view = view;
	}

	public void show() {
		answer = (String) JOptionPane.showInputDialog(view, message, title, JOptionPane.QUESTION_MESSAGE, null, null, defaultValue);	
	}
	
	public String getAnswer() {
		return answer;
	}
}
