package actions;

import java.io.IOException;

import main.JabberPoint;
import main.XMLAccessor;
import model.Presentation;

/**
 * @author peter
 *
 */
public class OpenFile implements Action {
	String filename;
	Presentation presentation;
	
	protected OpenFile() {
		filename = JabberPoint.TESTFILE;
		presentation = null;		
	}

	/* (non-Javadoc)
	 * @see actions.Action#performAction()
	 */
	@Override
	// TODO handle exception properly, also presentation == null
	public void performAction() {
		XMLAccessor accessor = new XMLAccessor();
		try {
			 accessor.loadFile(presentation, filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
