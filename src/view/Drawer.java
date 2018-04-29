
package view;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 * @author peter
 *
 */
public interface Drawer {
	
	// teken het item
		public void draw(int x, int y, float scale, 
				Graphics g, ImageObserver observer);
}
