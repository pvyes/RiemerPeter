package controller;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import model.Presentation;
import model.Slide;
import model.SlideItem;

/**
 * @author peter
 *
 */
public class MouseController extends MouseInputAdapter implements MouseListener {
	Presentation presentation;
	JFrame frame;
	
	public MouseController(JFrame f, Presentation p) {
		frame = f;
		presentation = p;
	}

	public void mouseClicked(MouseEvent e) {
		//get the position of the mouseclick
		Point2D p = e.getPoint();
		Slide slide = presentation.getCurrentSlide();
		Map<SlideItem, Rectangle> bbs = slide.getBoundingBoxes();
		for (Map.Entry<SlideItem, Rectangle> entry : bbs.entrySet()) {
			Rectangle r = entry.getValue();
			SlideItem si = entry.getKey();
			if (r.getFrame().contains(p) && si.getAction() != null) {
				si.getAction().performAction();
			}
		}		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
