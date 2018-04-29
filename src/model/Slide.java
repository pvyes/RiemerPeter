package model;

import java.util.Vector;

/** <p>Een slide. Deze klasse heeft tekenfunctionaliteit.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide {
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;
	/* Geen String meer maar een TextItem */
	protected TextItem title; // de titel wordt apart bewaard
	protected Vector<SlideItem> items; // de slide-items worden in een Vector bewaard

	public Slide() {
		items = new Vector<SlideItem>();
	}

	// Voeg een SlideItem toe
	public void append(SlideItem anItem) {
		items.addElement(anItem);
	}

	// geef de titel van de slide
	public String getTitle() {
		/* Geef nu de tekst van het TextItem terug */
		return title.getText();
	}

	// verander de titel van de slide
	public TextItem getTitleAsTextItem() {
		/* Geef nu de tekst van het TextItem terug */
		return title;
	}

	public void setTitle(String newTitle) {
		/* Creëer nu een TextItem op basis van de nieuwe titel */
		title = new TextItem(0, newTitle);
	}

	// Maak een TextItem van String, en voeg het TextItem toe
	public void append(int level, String message) {
		append(new TextItem(level, message));
	}

	// geef het betreffende SlideItem
	public SlideItem getSlideItem(int number) {
		return (SlideItem)items.elementAt(number);
	}

	// geef alle SlideItems in een Vector
	public Vector<SlideItem> getSlideItems() {
		return items;
	}

	// geef alle boundingBoxes in een Vector
/*	public Map<SlideItem, Rectangle> getBoundingBoxes() {
		return boundingBoxes;
	}
*/
	// geef de afmeting van de Slide
	public int getNumberOfSlideItems() {
		return items.size();
	}
}
