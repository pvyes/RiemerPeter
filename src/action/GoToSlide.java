package action;

import controller.InputInteraction;
import controller.UIFactory;
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
		InputInteraction ii = (InputInteraction) UIFactory.createUserInteraction(UIFactory.INPUT);
		int pageNumber = getPageNumberFromUser(ii);
		presentation.setSlideNumber(pageNumber - 1);		
	}

	private int getPageNumberFromUser(InputInteraction ii) {
		ii.setView(presentation.getShowView());
		ii.setTitle(TITLE);
		ii.setMessage(QUESTION);
		ii.setDefaultValue(DEFAULT_VALUE);
		ii.show();
		int pageNumber = Integer.parseInt(ii.getAnswer());
		return pageNumber;
	}

	public Presentation getPresentation() {
		return presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getKey() {		
		return key;
	}
}
