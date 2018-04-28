package controller;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * @author peter
 *
 */
public class ErrorInteraction implements UserInteraction {
	private String title;
	private String message;
	private String answer = "";
	private JComponent view;


	public void show() {
		JOptionPane.showMessageDialog(view, message, title, JOptionPane.ERROR_MESSAGE);	
	}
	
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

	public JComponent getView() {
		return view;
	}

	public void setView(JComponent view) {
		this.view = view;
	}

	public String getAnswer() {
		return answer;
	}
}
