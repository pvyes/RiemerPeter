
package actions;

import model.Presentation;

/**
 * @author peter
 *
 */
public class FirstSlide implements Action {
	private String key;
	private Presentation presentation;
	
	protected FirstSlide(Presentation presentation) {
		key = ActionFactory.FIRST_SLIDE;
		this.presentation = presentation;
	}
	
	public void performAction() {
		presentation.setSlideNumber(0);		
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
