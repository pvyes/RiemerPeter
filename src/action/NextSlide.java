package action;

import model.Presentation;

/**
 * @author peter
 *
 */
public class NextSlide implements Action {
	private String key;
	private Presentation presentation;
	
	protected NextSlide(Presentation presentation) {
		key = ActionFactory.NEXT_SLIDE;
		this.presentation = presentation;
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

	/* (non-Javadoc)
	 * @see actions.Action#performAction()
	 */
	@Override
	public void performAction() {
		int currentSlideNumber = presentation.getSlideNumber();
		if (currentSlideNumber < (presentation.getSize() - 1)) {
			presentation.setSlideNumber(currentSlideNumber + 1);
		}		
	}
	
	public String getKey() {
		return key;
	}
}
