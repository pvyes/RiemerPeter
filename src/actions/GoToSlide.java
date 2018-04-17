
package actions;

import model.Presentation;

/**
 * @author peter
 *
 */
public class GoToSlide implements Action {
	Presentation presentation;
	int pageNumber;
	
	protected GoToSlide() {
		presentation = null;
		pageNumber = 1;
		
	}

	/* (non-Javadoc)
	 * @see actions.Action#performAction()
	 */
	@Override
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
}
