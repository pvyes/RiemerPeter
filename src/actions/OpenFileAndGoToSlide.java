package actions;

import model.Presentation;

/**
 * @author peter
 *
 */
public class OpenFileAndGoToSlide extends CompositeAction {
	
	public OpenFileAndGoToSlide(Presentation presentation, String filename, int slidenumber) {
		super();
		Action openFile = new OpenFile(presentation, filename);
		Action goToSlide = new GoToSlide(presentation, slidenumber);
		actions.add(openFile);
		actions.add(goToSlide);		
	}

}
