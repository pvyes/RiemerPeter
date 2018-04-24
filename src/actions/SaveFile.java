package actions;

import java.io.IOException;

import main.JabberPoint;
import main.XMLAccessor;
import model.Presentation;

/**
 * @author peter
 *
 */
public class SaveFile implements Action {
	private String key;
	private String filename;
	private Presentation presentation;
	
	protected SaveFile(Presentation presentation) {
		key = ActionFactory.OPEN_FILE;
		filename = JabberPoint.TESTFILE;
		this.presentation = presentation;		
	}

	public void performAction() {
		XMLAccessor accessor = new XMLAccessor();
		try {
			accessor.saveFile(presentation, JabberPoint.SAVEFILE);
		} catch (IOException e) {
			e.printStackTrace();
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
