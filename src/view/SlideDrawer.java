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

import model.Slide;
import model.SlideItem;

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
	
	protected SlideDrawer(Slide slide, ImageObserver view) {
		this.slide = slide;
		this.view = view;
	}

	@Override
	public void draw(int x, int y, float scale, Graphics g, ImageObserver observer) {
		SlideViewerComponent mainframe = (SlideViewerComponent) observer;
	    int panelY = y; //holds the y-position relative to the main panel
		/* De titel hoeft niet meer apart behandeld te worden */
	    SlideItem title = slide.getTitleAsTextItem();
	    SlideItemDrawer titleDrawer = (SlideItemDrawer) DrawerFactory.createDrawer(title);
	    titleDrawer.draw(x, y, scale, g, view);
	    int titleHeight = titleDrawer.getBoundingBox(g, scale, view).height;
	    y += titleHeight;
	    panelY += y;
		for (int number=0; number<slide.getNumberOfSlideItems(); number++) {
	      SlideItem slideItem = (SlideItem)slide.getSlideItems().elementAt(number);
	      SlideItemDrawer sid = (SlideItemDrawer) DrawerFactory.createDrawer(slideItem);
	      sid.draw(x, y, scale, g, view);
	  	  int height = sid.getBoundingBox(g, scale, view).height;
	      Rectangle bb = sid.getBoundingBox(g, scale, view);
	      //if the slideItem has an associated action, draw a border rectangle an add it to the boundingboxes of the slide item
	      if (slideItem.getAction() != null) {
	    	  Rectangle borders = drawBorders(g, panelY, bb);
	    	  //correct the y-positions to catch mouseclicks with the title height
	    	  borders.setBounds(borders.x, borders.y + titleHeight, borders.width, borders.height);
		      mainframe.getBoundingBoxes().put(borders, slideItem.getAction());
	      }
	      y += height;
	      panelY += height;
	    }
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
