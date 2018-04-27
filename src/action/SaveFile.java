package action;

import java.io.IOException;

import accessor.Accessor;
import controller.UserInteraction;
import main.JabberPoint;
import main.JabberPointException;
import model.Presentation;

/**
 * @author peter
 *
 */
public class SaveFile implements Action {
    protected static final String IOE = "IOException";
    protected static final String IO_MESSAGE = "File could not be saved.";
    
	private static final String TITLE = "Bewaar bestand";
	private static final String QUESTION = "Geef de naam van het bestand";
	private static final String DEFAULT_VALUE = JabberPoint.SAVEFILE;
    
	private String key;
	private String filename;
	private Presentation presentation;
	private Accessor accessor;

	protected SaveFile(Presentation presentation) {
		key = ActionFactory.OPEN_FILE;
		filename = JabberPoint.TESTFILE;
		this.presentation = presentation;
	}

	public void performAction() {
		accessor = JabberPoint.getAccessor();
		UserInteraction ui = new UserInteraction(UserInteraction.INPUT, TITLE, QUESTION, DEFAULT_VALUE);
		String filename = ui.getAnswer();
		try {
			accessor.saveFile(presentation, filename);
		} catch (IOException e) {
			new JabberPointException(e, IOE, IO_MESSAGE);
		}
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the presentation
	 */
	public Presentation getPresentation() {
		return presentation;
	}

	/**
	 * @param presentation the presentation to set
	 */
	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	public String getKey() {
		return key;
	}
}
