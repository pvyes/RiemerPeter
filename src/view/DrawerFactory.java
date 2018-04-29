package view;

import javax.swing.JComponent;

import model.BitmapItem;
import model.Slide;
import model.SlideItem;
import model.TextItem;

/**
 * @author peter
 *
 */
public class DrawerFactory {
	
	public static Drawer createDrawer(Slide slide, JComponent view) {
		return new SlideDrawer(slide, view);
	}
	
	public static Drawer createDrawer(SlideItem slideitem, JComponent view) {
		if (slideitem instanceof TextItem) {
			return new TextItemDrawer((TextItem) slideitem);
		}
		if (slideitem instanceof BitmapItem) {
			return new BitmapItemDrawer((BitmapItem) slideitem);
		}
		return null;
	}
		
	public static Drawer createDrawer(SlideItem slideitem) {
		return createDrawer(slideitem, null);
	}
}
