package main;

import accessor.Accessor;
import accessor.XMLAccessor;
import controller.KeyController;
import controller.MenuController;
import controller.MouseController;
import model.Presentation;
import view.SlideViewerFrame;
import view.Style;

import java.io.IOException;

/** JabberPoint Main Programma
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class JabberPoint {
	public static final String TESTFILE = "actiontest.xml";
	public static final String SAVEFILE = "dump.xml";
	public static final String ABOUT = "JabberPoint is a primitive slide-show program in Java(tm). It\n" +
			"is freely copyable as long as you keep this notice and\n" +
			"the splash screen intact.\n" +
			"Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com.\n" +
			"Adapted by Gert Florijn (version 1.1) and " +
			"Sylvia Stuurman (version 1.2 and higher) for the Open" +
			"University of the Netherlands, 2002 -- now." +
			"Author's version available from http://www.darwinsys.com/";
	
	protected static final String IOERR = "IO Error: ";
	protected static final String JABERR = "Jabberpoint Error ";
	public static final String JABVERSION = "Jabberpoint 1.6 - OU version";
	
	private static Accessor accessor;

	/** Het Main Programma */
	public static void main(String argv[]) {
	
		Style.createStyles();
		Presentation presentation = new Presentation();
		try {
			if (argv.length == 0) { // een demo presentatie
				accessor = Accessor.getDemoAccessor();
				accessor.loadFile(presentation, "");
			} else {
				accessor = (XMLAccessor) new XMLAccessor();
				accessor.loadFile(presentation, argv[0]);
			}
			SlideViewerFrame mainframe = new SlideViewerFrame(JABVERSION, presentation);
			addControllers(mainframe, presentation);
			presentation.start();
		} catch (IOException ex) {
			new JabberPointException(ex, IOERR, ex.getMessage());
		}
	}
	
	public static Accessor getAccessor() {
		return accessor;
	}

	public static void setAccessor(Accessor accessor) {
		JabberPoint.accessor = accessor;
	}
	
	private static void addControllers(SlideViewerFrame mainframe, Presentation presentation) {
		mainframe.addKeyListener(new KeyController(presentation));
		mainframe.setMenuBar(new MenuController(mainframe, presentation));
		mainframe.addMouseListener(new MouseController(mainframe));		
	}
}
