package controller;
import java.awt.event.KeyEvent;

import actions.ActionFactory;
import actions.NextSlide;
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
	private ActionFactory af;

	public KeyController(Presentation p) {
		presentation = p;
		af = ActionFactory.getInstance(presentation);
	}

	public void keyPressed(KeyEvent keyEvent) {
		switch(keyEvent.getKeyCode()) {
			case KeyEvent.VK_PAGE_DOWN:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_ENTER:
			case '+':
				NextSlide ns = (NextSlide) af.getAction(ActionFactory.NEXT_SLIDE);
				ns.performAction();
				break;
			case KeyEvent.VK_PAGE_UP:
			case KeyEvent.VK_UP:
			case '-':
				PreviousSlide ps = (PreviousSlide) af.getAction(ActionFactory.PREVIOUS_SLIDE);
				ps.performAction();
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
}
