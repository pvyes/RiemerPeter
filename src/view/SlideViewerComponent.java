package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;

import action.Action;
import model.Presentation;
import model.Slide;

/** <p>SlideViewerComponent is een grafische component die Slides kan laten zien.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent {
		
	private Slide slide; // de huidige slide
	private Font labelFont = null; // het font voor labels
	private Presentation presentation = null; // de presentatie
	private JFrame frame = null;
	
	private static final long serialVersionUID = 227L;
	
	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100; //x-position of slide number information
	private static final int YPOS = 20;	//y-position of slide number information
	private static final int SLIDEWIDTH = 1200;
	private static final int SLIDEHEIGHT = 800;
	
	private Map<Rectangle, Action> boundingBoxes = new HashMap<Rectangle, Action>();

	public SlideViewerComponent(Presentation presentation, JFrame frame) {
		setBackground(BGCOLOR); 
		this.presentation = presentation;
		labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		this.frame = frame;
	}

	public Dimension getPreferredSize() {
		return new Dimension(SLIDEWIDTH, SLIDEHEIGHT);
	}
	
	// geef alle boundingBoxes in een Vector
	public Map<Rectangle, Action> getBoundingBoxes() {
	return boundingBoxes;
	}

	public void update(Presentation presentation, Slide data) {
		if (data == null) {
			repaint();
			return;
		}
		this.presentation = presentation;
		this.slide = data;
		repaint();
		frame.setTitle(presentation.getTitle());
	}

// teken de slide
	public void paintComponent(Graphics g) {
		g.setColor(BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		if (presentation.getSlideNumber() < 0 || slide == null) {
			return;
		}
		g.setFont(labelFont);
		g.setColor(COLOR);
		g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " +
                 presentation.getSize(), XPOS, YPOS);
		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		SlideDrawer sd = (SlideDrawer) DrawerFactory.createDrawer(slide, this);
		sd.draw(area.x, area.y, getScale(area), g, this);
	}
	
	// geef de schaal om de slide te kunnen tekenen
	private float getScale(Rectangle area) {
		return Math.min(((float)area.width) / ((float)SLIDEWIDTH), ((float)area.height) / ((float)SLIDEHEIGHT));
	}
}
