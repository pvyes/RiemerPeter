package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Slide;
import model.TextItem;

/**
 * @author peter
 *
 */
public class TextItemDrawer implements SlideItemDrawer {
	private TextItem textItem;
	private String text;
	private Style myStyle;
	
	public TextItemDrawer(TextItem item) {
		this.textItem = item;
		this.text = textItem.getText();
		this.myStyle = Style.getStyle(textItem.getLevel());
	}

	/* (non-Javadoc)
	 * @see view.Drawer#draw(int, int, float, java.awt.Graphics, view.Style, java.awt.image.ImageObserver)
	 */
	@Override
	public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
		if (text == null || text.length() == 0) {
			return;
		}
		List<TextLayout> layouts = getLayouts(g, myStyle, scale);
		Point pen = new Point(x + (int)(myStyle.getIndent() * scale), 
				y + (int) (myStyle.getLeading() * scale));
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(myStyle.getColor());
		Iterator<TextLayout> it = layouts.iterator();
		while (it.hasNext()) {
			TextLayout layout = it.next();
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}		
	}
	
	public void draw(Graphics g, Rectangle area, float scale, ImageObserver view) {
		if (text == null || text.length() == 0) {
			return;
		}
		List<TextLayout> layouts = getLayouts(g, myStyle, scale);
		Point pen = new Point(area.x + (int)(myStyle.getIndent() * scale), 
				area.y + (int) (myStyle.getLeading() * scale));
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(myStyle.getColor());
		Iterator<TextLayout> it = layouts.iterator();
		while (it.hasNext()) {
			TextLayout layout = it.next();
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}		
	}
	
	/* (non-Javadoc)
	 * @see view.SlideItemDrawer#getBoundingBox(java.awt.Graphics, java.awt.image.ImageObserver, float, view.Style)
	 */
	@Override
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
		List<TextLayout> layouts = getLayouts(g, myStyle, scale);
		int xsize = 0, ysize = (int) (myStyle.getLeading() * scale);
		Iterator<TextLayout> iterator = layouts.iterator();
		while (iterator.hasNext()) {
			TextLayout layout = iterator.next();
			Rectangle2D bounds = layout.getBounds();
			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}
			if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			}
			ysize += layout.getLeading() + layout.getDescent();
		}
		return new Rectangle((int) (myStyle.getIndent()*scale), 0, xsize, ysize );
	}
	
	private List<TextLayout> getLayouts(Graphics g, Style s, float scale) {
		List<TextLayout> layouts = new ArrayList<TextLayout>();
		AttributedString attrStr = getAttributedString(s, scale);
    	Graphics2D g2d = (Graphics2D) g;
    	FontRenderContext frc = g2d.getFontRenderContext();
    	LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
    	float wrappingWidth = (Slide.WIDTH - s.getIndent()) * scale;
    	while (measurer.getPosition() < text.length()) {
    		TextLayout layout = measurer.nextLayout(wrappingWidth);
    		layouts.add(layout);
    	}
    	return layouts;
	}

	public AttributedString getAttributedString(Style style, float scale) {
		AttributedString attrStr = new AttributedString(text);
		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
		return attrStr;
	}
}
