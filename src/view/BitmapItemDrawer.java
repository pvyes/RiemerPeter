package view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.JabberPointException;
import model.BitmapItem;

/**
 * @author peter
 *
 */
public class BitmapItemDrawer implements SlideItemDrawer {
	protected static final String IOE = "IOException ";
	protected static final String FILE = "Bestand ";
	protected static final String NOTFOUND = " niet gevonden";
	
	private BitmapItem bitmapItem;
	private Style myStyle;
	private BufferedImage bufferedImage;
	
	public BitmapItemDrawer(BitmapItem item) {
		this.bitmapItem = item;
		this.bufferedImage = getBufferedImage();
		this.myStyle = Style.getStyle(bitmapItem.getLevel());
	}	

	@Override
	public void draw(int x, int y, float scale, Graphics g, ImageObserver observer) {
		int width = x + (int) (myStyle.getIndent() * scale);
		int height = y + (int) (myStyle.getLeading() * scale);
		g.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(observer)*scale),
                (int) (bufferedImage.getHeight(observer)*scale), observer);
	}

	@Override
	public Rectangle getBoundingBox(Graphics g, float scale, ImageObserver observer) {
		return new Rectangle((int) (myStyle.getIndent() * scale), 0,
				(int) (bufferedImage.getWidth(observer) * scale),
				((int) (myStyle.getLeading() * scale)) + 
				(int) (bufferedImage.getHeight(observer) * scale));
	}
	
	private BufferedImage getBufferedImage() {
		try {
			return ImageIO.read(new File(bitmapItem.getName()));
		}
		catch (IOException e) {
			System.err.println(FILE + bitmapItem.getName() + NOTFOUND);
			new JabberPointException(e, IOE, FILE + bitmapItem.getName() + NOTFOUND);
		}
		return null;
	}

}
