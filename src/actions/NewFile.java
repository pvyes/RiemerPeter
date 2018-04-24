package actions;

import model.Presentation;

/**
 * @author peter
 *
 */
public class NewFile implements Action {
	private String key;
	private Presentation presentation;
	
	protected NewFile(Presentation presentation) {
		key = ActionFactory.NEW_FILE;
		this.presentation = presentation;
	}

	public void performAction() {
		presentation.clear();	
	}

	public String getKey() {
		return key;
	}
}
