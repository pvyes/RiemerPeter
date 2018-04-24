
package actions;

import model.Presentation;

/**
 * @author peter
 *
 */
public class LastSlide implements Action {
	private String key;
	private Presentation presentation;
	
	protected LastSlide(Presentation presentation) {
		key = ActionFactory.GO_TO_SLIDE;
		this.presentation = presentation;
	}
	
	public void performAction() {
		presentation.setSlideNumber(presentation.getSize() - 1);		
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
