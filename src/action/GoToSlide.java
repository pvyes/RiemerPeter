package action;

import controller.UserInteraction;
import model.Presentation;

/**
 * @author peter
 *
 */
public class GoToSlide implements Action {
	private static final String TITLE = "Ga naar ";
	private static final String QUESTION = "Geef de bladzijde:";
	private static final String DEFAULT_VALUE = "1";
	
	private String key;
	private Presentation presentation;
	private int pageNumber;
	
	protected GoToSlide(Presentation presentation) {
		key = ActionFactory.GO_TO_SLIDE;
		this.presentation = presentation;
		pageNumber = 1;
	}
	
	public void performAction() {
		UserInteraction ui = new UserInteraction(UserInteraction.INPUT, TITLE, QUESTION, DEFAULT_VALUE);
		int pageNumber = Integer.parseInt(ui.getAnswer());
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
