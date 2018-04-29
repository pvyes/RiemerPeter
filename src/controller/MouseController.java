package controller;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import action.Action;
import view.SlideViewerComponent;
import view.SlideViewerFrame;

/**
 * @author peter
 *
 */
public class MouseController extends MouseInputAdapter implements MouseListener {
	private SlideViewerFrame frame;
	private SlideViewerComponent svc;
	
	public MouseController(JFrame f) {
		frame = (SlideViewerFrame) f;
		svc = frame.getSlideViewerComponent();
	}

	public void mouseClicked(MouseEvent e) {
		//get the position of the mouseclick
		Point2D p = e.getPoint();
//		Map<SlideItem, Rectangle> bbs = svc.getBoundingBoxes();
		Map<Rectangle, Action> bbs = svc.getBoundingBoxes();
//		for (Map.Entry<SlideItem, Rectangle> entry : bbs.entrySet()) {
		for (Map.Entry<Rectangle, Action> entry : bbs.entrySet()) {
			Rectangle r = entry.getKey();
//			SlideItem si = entry.getKey();
//			if (r.getFrame().contains(p) && si.getAction() != null) {
			if (r.getFrame().contains(p)) {
				entry.getValue().performAction();
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
