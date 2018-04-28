package model;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import view.Style;

/** <p>Een slide. Deze klasse heeft tekenfunctionaliteit.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide {
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;
	/* Geen String meer maar een TextItem */
	protected TextItem title; // de titel wordt apart bewaard
	protected Vector<SlideItem> items; // de slide-items worden in een Vector bewaard
	//TODO
//	protected Map<SlideItem, Rectangle> boundingBoxes = new HashMap<SlideItem, Rectangle>();

	public Slide() {
		items = new Vector<SlideItem>();
	}

	// Voeg een SlideItem toe
	public void append(SlideItem anItem) {
		items.addElement(anItem);
	}

	// geef de titel van de slide
	public String getTitle() {
		/* Geef nu de tekst van het TextItem terug */
		return title.getText();
	}

	// verander de titel van de slide
	public TextItem getTitleAsTextItem() {
		/* Geef nu de tekst van het TextItem terug */
		return title;
	}

	public void setTitle(String newTitle) {
		/* Creëer nu een TextItem op basis van de nieuwe titel */
		title = new TextItem(0, newTitle);
	}

	// Maak een TextItem van String, en voeg het TextItem toe
	public void append(int level, String message) {
		append(new TextItem(level, message));
	}

	// geef het betreffende SlideItem
	public SlideItem getSlideItem(int number) {
		return (SlideItem)items.elementAt(number);
	}

	// geef alle SlideItems in een Vector
	public Vector<SlideItem> getSlideItems() {
		return items;
	}

	// geef alle boundingBoxes in een Vector
/*	public Map<SlideItem, Rectangle> getBoundingBoxes() {
		return boundingBoxes;
	}
*/
	// geef de afmeting van de Slide
	public int getNumberOfSlideItems() {
		return items.size();
	}
/*
	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);
	    int y = area.y;
	    int panelY = y; //holds the y-position relative to the main panel
		/* De titel hoeft niet meer apart behandeld te worden */
/*	    SlideItem slideItem = this.title;
	    Style style = Style.getStyle(slideItem.getLevel());
	    slideItem.draw(area.x, y, scale, g, style, view);
	    int titleHeight = slideItem.getBoundingBox(g, view, scale, style).height;
	    y += titleHeight;
	    panelY += y;
		for (int number=0; number<getSize(); number++) {
	      slideItem = (SlideItem)getSlideItems().elementAt(number);
	      style = Style.getStyle(slideItem.getLevel());
	      slideItem.draw(area.x, y, scale, g, style, view);
	      int height = slideItem.getBoundingBox(g, view, scale, style).height;
	      Rectangle bb = slideItem.getBoundingBox(g, view, scale, style);
	      //if the slideItem has an associated action, draw a border rectangle an add it to the boundingboxes of the slide item
	      if (slideItem.getAction() != null) {
	    	  Rectangle borders = drawBorders(g, panelY, bb);
	    	  //correct the y-positions to catch mouseclicks with the title height
	    	  borders.setBounds(borders.x, borders.y + titleHeight, borders.width, borders.height);
		      boundingBoxes.put(slideItem, borders);
	      }
	      y += height;
	      panelY += height;
	    }
	  }

	// geef de schaal om de slide te kunnen tekenen
	private float getScale(Rectangle area) {
		return Math.min(((float)area.width) / ((float)WIDTH), ((float)area.height) / ((float)HEIGHT));
	}

	private Rectangle drawBorders(Graphics g, int panelY, Rectangle rect) {
/*		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(STROKE_THICKNESS));
        g2.setColor(Color.LIGHT_GRAY);
        Rectangle borderRectangle = new Rectangle(rect.x - BORDER_OFFSET_LEFT, panelY - BORDER_OFFSET_TOP, rect.width + BORDER_OFFSET_RIGHT, rect.height + BORDER_OFFSET_BOTTOM);
        g2.draw(borderRectangle);
        return borderRectangle;
   return null;}
*/}
