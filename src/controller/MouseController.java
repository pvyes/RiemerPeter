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
		Map<Rectangle, Action> bbs = svc.getBoundingBoxes();
		for (Map.Entry<Rectangle, Action> entry : bbs.entrySet()) {
			Rectangle r = entry.getKey();
			if (r.getFrame().contains(p)) {
				entry.getValue().performAction();
			}
		}		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
