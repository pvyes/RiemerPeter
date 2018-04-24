package main;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import actions.Action;
import actions.ActionFactory;
import model.BitmapItem;
import model.Presentation;
import model.Slide;
import model.SlideItem;
import model.TextItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/** XMLAccessor, reads and writes XML files
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class XMLAccessor extends Accessor {
	
    /** Default API to use. */
    protected static final String DEFAULT_API_TO_USE = "dom";
    
    /** namen van xml tags of attributen */
    protected static final String SLIDESHOW = "slideshow";
    protected static final String HEAD = "head";
    protected static final String TITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEMS = "items";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";
    protected static final String ACTION = "action";
    protected static final String NAME = "name";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    
    /** tekst van messages */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";
    
    
    private String getTitle(Element element, String tagName) {
    	NodeList titles = element.getElementsByTagName(tagName);
    	return titles.item(0).getTextContent();
    	
    }

	public void loadFile(Presentation presentation, String filename) throws IOException {
		int slideNumber, itemNumber, max = 0, maxItems = 0;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();    
			Document document = builder.parse(new File(filename)); // maak een JDOM document
			Element doc = document.getDocumentElement();
			Element head =  (Element) doc.getElementsByTagName(HEAD).item(0); //there's only one head-element
			presentation.setTitle(getTitle(head, TITLE));

			NodeList slides = doc.getElementsByTagName(SLIDE);
			max = slides.getLength();
			for (slideNumber = 0; slideNumber < max; slideNumber++) {
				Element xmlSlide = (Element) slides.item(slideNumber);
				Slide slide = new Slide();
				slide.setTitle(getTitle(xmlSlide, TITLE));
				presentation.append(slide);
				NodeList items = xmlSlide.getElementsByTagName(ITEMS); //There is only one <items>
				NodeList slideItems = items.item(0).getChildNodes();
				addSlideItems(presentation, slide, slideItems, null);
			}
		} 
		catch (IOException iox) {
			System.err.println(iox.toString());
		}
		catch (SAXException sax) {
			System.err.println(sax.getMessage());
		}
		catch (ParserConfigurationException pcx) {
			System.err.println(PCE);
		}
		
	}

	/**
	 * Sets the slideitems, given in the NodeList items, to the slide.
	 * @param slide
	 * @param items
	 */
	private void addSlideItems(Presentation presentation, Slide slide, NodeList slideItems, Action action) {
		SlideItem si = null;
		int itemsLength = slideItems.getLength();
		for (int i = 0; i < itemsLength; i++) {
			String tag = slideItems.item(i).getNodeName();
			//text
			if (tag.equals(TEXT)) {
				si = createTextItem(slideItems.item(i));
				if (action != null) {
					si.setAction(action);
				}
				slide.append(si);
			}
			//image
			if (tag.equals(IMAGE)) {
				si = createBitmapItem(slideItems.item(i));
				if (action != null) {
					si.setAction(action);
				}
				slide.append(si);
			}
			//action
			if (tag.equals(ACTION)) {
				createAction(presentation, slide, slideItems.item(i), action);
			}
		}
	}

	/**
	 * @param item
	 * @return
	 */
	private Action createAction(Presentation presentation, Slide slide, Node item, Action action) {		
		NamedNodeMap attributes = item.getAttributes();
		String actionkey = attributes.getNamedItem(NAME).getTextContent();
		ActionFactory af = ActionFactory.getInstance(presentation);
		if (action == null) {
			action = af.getAction(actionkey);
		} else {
			List<Action> actions = new ArrayList<Action>();
			Action newAction = af.getAction(actionkey);
			actions.add(action);
			actions.add(newAction);
			action = af.getAction(action.getKey() + "_" + actionkey, actions);
		}
		NodeList items = item.getChildNodes();
		addSlideItems(presentation, slide, items, action);
		return action;
	}

	/**
	 * @param slide
	 * @param item
	 */
	private TextItem createTextItem(Node item) {
		int level = 1; // default
		NamedNodeMap attributes = item.getAttributes();
		String leveltext = attributes.getNamedItem(LEVEL).getTextContent();
		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			}
			catch(NumberFormatException x) {
				System.err.println(NFE);
			}
		}
		return new TextItem(level, item.getTextContent());		
	}

	/**
	 * @param slide
	 * @param item
	 */
	private BitmapItem createBitmapItem(Node item) {
		int level = 1; // default
		NamedNodeMap attributes = item.getAttributes();
		String leveltext = attributes.getNamedItem(LEVEL).getTextContent();
		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			}
			catch(NumberFormatException x) {
				System.err.println(NFE);
			}
		}
		return new BitmapItem(level, item.getTextContent());		
	}
	/**
	 * @param item
	 * @return
	 */
/*
 * 	private Action loadAction(Element item) {
 */
/*		Action action = null;
		ActionFactory af = ActionFactory.getInstance();
		List<Action> actions = new ArrayList<Action>();
		String dummy = item.getFirstChild().getNodeName();
		if (item.getFirstChild().getNodeName().equals(ACTION)) {
			item = (Element) item.getFirstChild();
			action = af.getAction(item.getAttribute(NAME));
		}
		return action;
	}
*/

/*	
	protected void loadSlideItem(Slide slide, Element item) {
		int level = 1; // default
		NamedNodeMap attributes = item.getAttributes();
		String leveltext = attributes.getNamedItem(LEVEL).getTextContent();
		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			}
			catch(NumberFormatException x) {
				System.err.println(NFE);
			}
		}
		String type = attributes.getNamedItem(KIND).getTextContent();
		if (TEXT.equals(type)) {
			slide.append(new TextItem(level, item.getTextContent()));
		}
		else {
			if (IMAGE.equals(type)) {
				slide.append(new BitmapItem(level, item.getTextContent()));
			}
			else {
				System.err.println(UNKNOWNTYPE);
			}
		}
	}
*/
	public void saveFile(Presentation presentation, String filename) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(filename));
		out.println("<?xml version=\"1.0\"?>");
		out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
		out.println("<presentation>");
		out.print("<showtitle>");
		out.print(presentation.getTitle());
		out.println("</showtitle>");
		for (int slideNumber=0; slideNumber<presentation.getSize(); slideNumber++) {
			Slide slide = presentation.getSlide(slideNumber);
			out.println("<slide>");
			out.println("<title>" + slide.getTitle() + "</title>");
			Vector<SlideItem> slideItems = slide.getSlideItems();
			for (int itemNumber = 0; itemNumber<slideItems.size(); itemNumber++) {
				SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
				out.print("<item kind="); 
				if (slideItem instanceof TextItem) {
					out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
					out.print( ( (TextItem) slideItem).getText());
				}
				else {
					if (slideItem instanceof BitmapItem) {
						out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
						out.print( ( (BitmapItem) slideItem).getName());
					}
					else {
						System.out.println("Ignoring " + slideItem);
					}
				}
				out.println("</item>");
			}
			out.println("</slide>");
		}
		out.println("</presentation>");
		out.close();
	}
}
