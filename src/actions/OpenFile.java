package actions;

import java.io.IOException;
import main.XMLAccessor;
import model.Presentation;

/**
 * @author peter
 *
 */
public class OpenFile implements Action {
	String filename;
	Presentation presentation;
	
	public OpenFile(Presentation presentation, String filename) {
		this.filename = filename;
		this.presentation = presentation;		
	}

	/* (non-Javadoc)
	 * @see actions.Action#performAction()
	 */
	@Override
	// TODO handle exception properly
	public void performAction() {
		XMLAccessor accessor = new XMLAccessor();
		try {
			 accessor.loadFile(presentation, filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		presentation.setSlideNumber(0);
	}

}
