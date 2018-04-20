package controller;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import actions.Action;
import actions.ActionFactory;
import actions.Beep;
import actions.CompositeAction;
import actions.GoToSlide;
import actions.NextSlide;
import actions.OpenFile;
import actions.PreviousSlide;
import actions.SystemExit;
import main.JabberPoint;
import model.Presentation;

import java.awt.event.KeyAdapter;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class KeyController extends KeyAdapter {
	private Presentation presentation; // Er worden commando's gegeven aan de presentatie
	private ActionFactory af = ActionFactory.getInstance();

	public KeyController(Presentation p) {
		presentation = p;
	}

	public void keyPressed(KeyEvent keyEvent) {
		switch(keyEvent.getKeyCode()) {
			case KeyEvent.VK_PAGE_DOWN:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_ENTER:
			case '+':
				NextSlide ns = (NextSlide) af.getAction(ActionFactory.NEXT_SLIDE);
				ns.setPresentation(presentation);
				ns.performAction();
				break;
			case KeyEvent.VK_PAGE_UP:
			case KeyEvent.VK_UP:
			case '-':
				PreviousSlide ps = (PreviousSlide) af.getAction(ActionFactory.PREVIOUS_SLIDE);
				ps.setPresentation(presentation);
				ps.performAction();
				break;
			case 'b':
			case 'B':
			CompositeAction bogb = makeCompositeAction();
				bogb.performAction();
				break;
			case 'q':
			case 'Q':
				SystemExit se = (SystemExit) af.getAction(ActionFactory.SYSTEM_EXIT);
				se.performAction();
				break; // wordt nooit bereikt als het goed is
			default:
				break;
		}
	}

	/**
	 * @return
	 */
	private CompositeAction makeCompositeAction() {
		CompositeAction bogb = new CompositeAction("bogb");
		//beep, Openfile, go to page 3 and beep
		bogb.addAction(ActionFactory.getInstance().getAction(ActionFactory.BEEP)); 
		bogb.addAction(ActionFactory.getInstance().getAction(ActionFactory.OPEN_FILE));
		bogb.addAction(ActionFactory.getInstance().getAction(ActionFactory.GO_TO_SLIDE));
		bogb.addAction(ActionFactory.getInstance().getAction(ActionFactory.BEEP));
		List<Action> result = bogb.getActionsByKey(ActionFactory.GO_TO_SLIDE);
		for (int i = 0; i < result.size(); i ++) {
			//parameter passed by reference
			GoToSlide gts = (GoToSlide) result.get(i);
			gts.setPresentation(presentation);
			gts.setPageNumber(3);;
		}
		List<Action> resultOpenFile = bogb.getActionsByKey(ActionFactory.OPEN_FILE);
		for (int i = 0; i < resultOpenFile.size(); i ++) {
			//parameter passed by reference
			OpenFile of = (OpenFile) resultOpenFile.get(i);
			of.setPresentation(presentation);
			of.setFilename(JabberPoint.TESTFILE);
		}
		return bogb;
	}
}
