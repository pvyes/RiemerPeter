package controller;
import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import actions.ActionFactory;
import actions.GoToSlide;
import actions.NewFile;
import actions.NextSlide;
import actions.OpenFile;
import actions.PreviousSlide;
import actions.SaveFile;
import actions.SystemExit;

import main.AboutBox;
import main.JabberPoint;

import model.Presentation;

/** <p>De controller voor het menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {
	
	private Frame parent; // het frame, alleen gebruikt als ouder voor de Dialogs
	private ActionFactory af;
	
	private static final long serialVersionUID = 227L;
	
	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";
	
	protected static final String PAGENR = "Page number?";
	
	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	public MenuController(Frame frame, Presentation pres) {
		parent = frame;
		af = ActionFactory.getInstance(pres);
		MenuItem menuItem;
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(menuItem = mkMenuItem(OPEN));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				OpenFile openFile = (OpenFile) af.getAction(ActionFactory.OPEN_FILE);
				openFile.setFilename(JabberPoint.TESTFILE);
				openFile.performAction();
/*					xmlAccessor.loadFile(presentation, TESTFILE);
					presentation.setSlideNumber(0);
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(parent, IOEX + exc, 
         			LOADERR, JOptionPane.ERROR_MESSAGE);
				}
*///				parent.repaint();
			}
		} );
		fileMenu.add(menuItem = mkMenuItem(NEW));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				NewFile newFile = (NewFile) af.getAction(ActionFactory.NEW_FILE);
				newFile.performAction();
//				parent.repaint();
			}
		});
		fileMenu.add(menuItem = mkMenuItem(SAVE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveFile saveFile = (SaveFile) af.getAction(ActionFactory.SAVE_FILE);
				saveFile.setFilename(JabberPoint.SAVEFILE);
				saveFile.performAction();
/*				Accessor xmlAccessor = new XMLAccessor();
				try {
					xmlAccessor.saveFile(presentation, SAVEFILE);
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(parent, IOEX + exc, 
							SAVEERR, JOptionPane.ERROR_MESSAGE);
				}
*/			}
		});
		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				SystemExit se = (SystemExit) af.getAction(ActionFactory.SYSTEM_EXIT);
				se.performAction();
			}
		});
		add(fileMenu);
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(menuItem = mkMenuItem(NEXT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				NextSlide nextSlide = (NextSlide) af.getAction(ActionFactory.NEXT_SLIDE);
				nextSlide.performAction();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(PREV));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				PreviousSlide prevSlide = (PreviousSlide) af.getAction(ActionFactory.PREVIOUS_SLIDE);
				prevSlide.performAction();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(GOTO));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String pageNumberStr = JOptionPane.showInputDialog((Object)PAGENR);
				int pageNumber = Integer.parseInt(pageNumberStr);
				GoToSlide goToSlide = (GoToSlide)af.getAction(ActionFactory.GO_TO_SLIDE);
				goToSlide.setPageNumber(pageNumber);
				goToSlide.performAction();
			}
		});
		add(viewMenu);
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AboutBox.show(parent);
			}
		});
		setHelpMenu(helpMenu);		// nodig for portability (Motif, etc.).
	}

// een menu-item aanmaken
	public MenuItem mkMenuItem(String name) {
		char shortcut = name.charAt(0);
		//Avoid double shortcuts ('N' of "New" and "Next")
		if (name.equals(NEXT)) shortcut = 'X';
		return new MenuItem(name, new MenuShortcut(shortcut));
	}
}
