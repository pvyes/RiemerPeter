package action;

import java.io.IOException;

import accessor.XMLAccessor;
import controller.InputInteraction;
import controller.UIFactory;
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
		String filename = getFileNameFromUser();
		try {
			accessor.loadFile(presentation, filename);
		} catch (IOException e) {
			new JabberPointException(e, IOE, IO_MESSAGE);
		}
		presentation.setSlideNumber(0);
	}

	private String getFileNameFromUser() {
		InputInteraction ii = (InputInteraction) UIFactory.createUserInteraction(UIFactory.INPUT);
		ii.setView(presentation.getShowView());
		ii.setTitle(TITLE);
		ii.setMessage(QUESTION);
		ii.setDefaultValue(DEFAULT_VALUE);
		ii.show();
		String filename = ii.getAnswer();
		return filename;
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
