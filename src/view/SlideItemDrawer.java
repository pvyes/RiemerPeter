/**
 * 
 */
package view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * @author peter
 *
 */
public interface SlideItemDrawer extends Drawer {
	
	// Geef de bounding box
		public Rectangle getBoundingBox(Graphics g, 
				ImageObserver observer, float scale, Style style);
}
