package actions;

import main.JabberPoint;
import model.Presentation;

/**
 * @author peter
 *
 */
public class OpenFileAndGoToSlide extends CompositeAction {
	
	private Presentation presentation;
	private String filename;
	private int slidenumber;
	
	private OpenFile openFile;
	private GoToSlide goToSlide;
	
	protected OpenFileAndGoToSlide() {
		super();
		presentation = null;
		filename = JabberPoint.TESTFILE;
		slidenumber = 1;
		
		openFile = new OpenFile();
		goToSlide = new GoToSlide();
		actions.add(openFile);
		actions.add(goToSlide);		
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
		openFile.setPresentation(presentation);
		goToSlide.setPresentation(presentation);
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
		openFile.setFilename(filename);
		this.filename = filename;
	}

	/**
	 * @return the slidenumber
	 */
	public int getSlidenumber() {
		return slidenumber;
	}

	/**
	 * @param slidenumber the slidenumber to set
	 */
	public void setSlidenumber(int slidenumber) {
		this.slidenumber = slidenumber;
		goToSlide.setPageNumber(slidenumber);
	}
}
