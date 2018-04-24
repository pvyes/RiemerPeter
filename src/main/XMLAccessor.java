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
import actions.CompositeAction;
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
    
	public void loadFile(Presentation presentation, String filename) throws IOException {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();    
			Document document = builder.parse(new File(filename)); // maak een JDOM document
			Element doc = document.getDocumentElement();
			
			parseHeader(presentation, doc);
			parseSlides(presentation, doc);
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
	 * Parses the header of the xml document and modifies the given presentation accordingly.
	 * @param presentation
	 * @param doc
	 */
	private void parseHeader(Presentation presentation, Element doc) {
		Element head =  (Element) doc.getElementsByTagName(HEAD).item(0); //there's only one head-element
    	String title = head.getTextContent();
		presentation.setTitle(title);
	}

	/**
	 * Parses the slides of the xml document and modifies the given presentation accordingly.
	 * @param presentation
	 * @param doc
	 */
	private void parseSlides(Presentation presentation, Element doc) {
		int slideNumber;
		int max;
		NodeList slides = doc.getElementsByTagName(SLIDE);
		max = slides.getLength();
		for (slideNumber = 0; slideNumber < max; slideNumber++) {
			//slidetitles
			Element xmlSlide = (Element) slides.item(slideNumber);
			Slide slide = new Slide();
			parseSlideTitle(xmlSlide, slide);
			
			//slideItems
			NodeList items = xmlSlide.getElementsByTagName(ITEMS); //There is only one <items>
			NodeList slideItems = items.item(0).getChildNodes();
			parseSlideItems(presentation, slide, slideItems, null);
			
			presentation.append(slide);
		}
	}

	/**
	 * Parses the title of the xml slide item and modifies the given slide accordingly.
	 * @param xmlSlide
	 * @param slide
	 */
	private void parseSlideTitle(Element xmlSlide, Slide slide) {
    	NodeList titles = xmlSlide.getElementsByTagName(TITLE);
    	String title = titles.item(0).getTextContent();
		slide.setTitle(title);
	}

	/**
	 * Parses the SlideItems, given in the NodeList 'items', and add them to the slide.
	 * @param slide
	 * @param items
	 */
	private void parseSlideItems(Presentation presentation, Slide slide, NodeList slideItems, Action action) {
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
				createAndAddAction(presentation, slide, slideItems.item(i), action);
			}
		}
	}

	/** Creates an action and adds it to the nested SlideItems.
	 * @param item
	 * @return
	 */
	private Action createAndAddAction(Presentation presentation, Slide slide, Node item, Action action) {		
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
		parseSlideItems(presentation, slide, items, action);
		return action;
	}

	/** Creates a textItem.
	 * @param slide
	 * @param item
	 * @return a TextItem
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

	/** Creates a BitmapItem.
	 * @param slide
	 * @param item
	 * @return a BitMpItem
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

	public void saveFile(Presentation presentation, String filename) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(filename));
		out.println("<?xml version=\"1.0\"?>");
		out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
		out.println("<presentation>");
		out.println("<slideshow>");
		out.println("<head>");
		out.println("<title>");
		out.println(presentation.getTitle());
		out.println("</title>");
		out.println("</head>");
		for (int slideNumber=0; slideNumber<presentation.getSize(); slideNumber++) {
			Slide slide = presentation.getSlide(slideNumber);
			out.println("<slide>");
			out.println("<title>" + slide.getTitle() + "</title>");
			Vector<SlideItem> slideItems = slide.getSlideItems();
			out.println("<items>");
			for (int itemNumber = 0; itemNumber<slideItems.size(); itemNumber++) {
				int numberOfActions = 0;
				SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
				if (slideItem.getAction() != null) {
					numberOfActions = printActionBeginTags(out, slideItem.getAction());
				}
				if (slideItem instanceof TextItem) {
					out.println("<text level=\"" + slideItem.getLevel() + "\">");
					out.print( ( (TextItem) slideItem).getText());
					out.print("</text>");
					out.println();
				}
				else {
					if (slideItem instanceof BitmapItem) {
						out.println("<image level=\"" + slideItem.getLevel() + "\">");
						out.print( ( (BitmapItem) slideItem).getName());
						out.print("</image>");
						out.println();
					}
					else {
						System.out.println("Ignoring " + slideItem);
					}
				}
				for (; numberOfActions > 0; numberOfActions--) {
					out.print("</action>");
				}
			}
			out.println("</items>");
			out.println("</slide>");
		}
		out.println("</slideshow>");
		out.println("</presentation>");
		out.close();
	}

	/**
	 * @param out
	 * @param action2
	 */
	private int printActionBeginTags(PrintWriter out, Action action) {
		int nr = 0;
		if (action instanceof CompositeAction) {
			CompositeAction ca = (CompositeAction) action;
			for(Action a : ca.getActions()) {
				out.println("<action name=\"" + a.getKey() + "\">");
				nr++;
			}
		} else {
			out.println("<action name=\"" + action.getKey() + "\">");	
			nr++;
		}
		return nr;
	}
}
