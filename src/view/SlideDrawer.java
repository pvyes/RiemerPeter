/**
 * 
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import model.BitmapItem;
import model.Slide;
import model.SlideItem;
import model.TextItem;

/**
 * @author peter
 *
 */
public class SlideDrawer implements Drawer {
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;
	private static final float STROKE_THICKNESS = 2;
	private static final int BORDER_OFFSET_LEFT = 2;
	private static final int BORDER_OFFSET_BOTTOM = -8;
	private static final int BORDER_OFFSET_TOP = 8;
	private static final int BORDER_OFFSET_RIGHT = 8;
	
	private Slide slide;
	private ImageObserver view;
	
	public SlideDrawer(Slide slide, ImageObserver view) {
		this.slide = slide;
		this.view = view;
	}
	
	/* (non-Javadoc)
	 * @see view.Drawer#draw(int, int, float, java.awt.Graphics, view.Style, java.awt.image.ImageObserver)
	 */
	@Override
	public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
	    int panelY = y; //holds the y-position relative to the main panel
		/* De titel hoeft niet meer apart behandeld te worden */
	    SlideItem slideItem = slide.getTitleAsTextItem();
//	    slideItem.draw(area.x, y, scale, g, style, view); /TODO area in method or x en y...?
	    SlideItemDrawer sd = new TextItemDrawer((TextItem) slideItem);
	    sd.draw(x, y, scale, g, style, view);
	    int titleHeight = sd.getBoundingBox(g, view, scale, style).height;
	    y += titleHeight;
	    panelY += y;
		for (int number=0; number<slide.getSize(); number++) {
	      slideItem = (SlideItem)slide.getSlideItems().elementAt(number);
	      style = Style.getStyle(slideItem.getLevel());
//	      slideItem.draw(area.x, y, scale, g, style, view);
	      if (slideItem instanceof TextItem) {
			  sd = new TextItemDrawer((TextItem) slideItem);	    	  
	      }
	      if (slideItem instanceof BitmapItem) {
			  sd = new BitmapItemDrawer((BitmapItem) slideItem);	    	  
	      }
	      sd.draw(x, y, scale, g, style, view);
//	      slideItem.draw(x, y, scale, g, style, view);
	  	  int height = sd.getBoundingBox(g, view, scale, style).height;
	      Rectangle bb = sd.getBoundingBox(g, view, scale, style);
	      //if the slideItem has an associated action, draw a border rectangle an add it to the boundingboxes of the slide item
	      if (slideItem.getAction() != null) {
	    	  Rectangle borders = drawBorders(g, panelY, bb);
	    	  //correct the y-positions to catch mouseclicks with the title height
	    	  borders.setBounds(borders.x, borders.y + titleHeight, borders.width, borders.height);
		      slide.getBoundingBoxes().put(slideItem, borders);
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
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(STROKE_THICKNESS));
        g2.setColor(Color.LIGHT_GRAY);
        Rectangle borderRectangle = new Rectangle(rect.x - BORDER_OFFSET_LEFT, panelY - BORDER_OFFSET_TOP, rect.width + BORDER_OFFSET_RIGHT, rect.height + BORDER_OFFSET_BOTTOM);
        g2.draw(borderRectangle);
        return borderRectangle;
    }

}