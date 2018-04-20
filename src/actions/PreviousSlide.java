package actions;

import model.Presentation;

/**
 * @author peter
 *
 */
public class PreviousSlide implements Action {
	private String key;
	private Presentation presentation;
	
	protected PreviousSlide() {
		key = ActionFactory.PREVIOUS_SLIDE;
		presentation = null;
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

	public void performAction() {
		int currentSlideNumber = presentation.getSlideNumber();
		if (currentSlideNumber > 0) {
			presentation.setSlideNumber(currentSlideNumber - 1);
		}		
	}

	public String getKey() {
		return key;
	}

}
