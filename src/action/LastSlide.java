
package action;

import model.Presentation;

/**
 * @author peter
 *
 */
public class LastSlide implements Action {
	private String key;
	private Presentation presentation;
	
	protected LastSlide(Presentation presentation) {
		key = ActionFactory.LAST_SLIDE;
		this.presentation = presentation;
	}
	
	public void performAction() {
		presentation.setSlideNumber(presentation.getSize() - 1);		
	}

	public Presentation getPresentation() {
		return presentation;
	}
  
	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	public String getKey() {		
		return key;
	}
}
