package action;

import java.io.IOException;

import accessor.Accessor;
import controller.InputInteraction;
import controller.UIFactory;
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
		String filename = getFileNameFromUser();
		try {
			accessor.saveFile(presentation, filename);
		} catch (IOException e) {
			new JabberPointException(e, IOE, IO_MESSAGE);
		}
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Presentation getPresentation() {
		return presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	public String getKey() {
		return key;
	}
}
