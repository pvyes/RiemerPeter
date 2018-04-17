
package actions;

import model.Presentation;

/**
 * @author peter
 *
 */
public class GoToSlide implements Action {
	Presentation presentation;
	int pageNumber;
	
	public GoToSlide(Presentation presentation, int pageNumber) {
		this.presentation = presentation;
		this.pageNumber = pageNumber;
		
	}

	/* (non-Javadoc)
	 * @see actions.Action#performAction()
	 */
	@Override
	public void performAction() {
		presentation.setSlideNumber(pageNumber - 1);		
	}

}
