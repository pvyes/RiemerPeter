package action;

import java.io.IOException;

import accessor.XMLAccessor;
import controller.UserInteraction;
import main.JabberPoint;
import main.JabberPointException;
import model.Presentation;

/**
 * @author peter
 *
 */
public class OpenFile implements Action {
    protected static final String IOE = "IOException";
    protected static final String IO_MESSAGE = "File could not be opened.";
    
	private static final String TITLE = "Open bestand";
	private static final String QUESTION = "Geef de naam van het bestand:";
	private static final String DEFAULT_VALUE = JabberPoint.TESTFILE;
	
	private String key;
	private String filename;
	private Presentation presentation;
	
	protected OpenFile(Presentation presentation) {
		key = ActionFactory.OPEN_FILE;
		filename = JabberPoint.TESTFILE;
		this.presentation = presentation;		
	}

	public void performAction() {
		XMLAccessor accessor = new XMLAccessor();
		presentation.clear();
		UserInteraction ui = new UserInteraction(UserInteraction.INPUT, TITLE, QUESTION, DEFAULT_VALUE);
		String filename = ui.getAnswer();
		try {
			accessor.loadFile(presentation, filename);
		} catch (IOException e) {
			new JabberPointException(e, IOE, IO_MESSAGE);
		}
		presentation.setSlideNumber(0);
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
