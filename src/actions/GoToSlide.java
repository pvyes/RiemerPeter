
package actions;

import model.Presentation;

/**
 * @author peter
 *
 */
public class GoToSlide implements Action {
	private String key;
	private Presentation presentation;
	private int pageNumber;
	
	protected GoToSlide(Presentation presentation) {
		key = ActionFactory.GO_TO_SLIDE;
		this.presentation = presentation;
		pageNumber = 1;
	}
	
	public void performAction() {
		presentation.setSlideNumber(pageNumber - 1);		
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

	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getKey() {		
		return key;
	}
}
