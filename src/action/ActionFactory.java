package action;

import java.util.HashMap;
import java.util.List;

import main.JabberPointException;
import model.Presentation;

/**
 * @author peter
 *
 */
public class ActionFactory {
	public static final String ACTION_ERROR = "Action error";
	public static final String ACTION_ERROR_MESSAGE = "De gevraagde actie is niet gekend.";
	
	public static final String OPEN_FILE = "openfile";
	public static final String SYSTEM_EXIT = "systemexit";
	public static final String GO_TO_SLIDE = "gotoslide";
	public static final String NEXT_SLIDE = "nextslide";
	public static final String PREVIOUS_SLIDE = "previousslide";
	public static final String FIRST_SLIDE = "firstslide";
	public static final String LAST_SLIDE = "lastslide";
	public static final String BEEP = "beep";
	public static final String NEW_FILE = "newfile";
	public static final String SAVE_FILE = "savefile";
	public static final String ABOUT = "about";
	
	private static Presentation presentation;
	
    private static final ActionFactory instance = new ActionFactory();
    private static HashMap<String, Action> actionPool;

    
    //private constructor to avoid client applications to use constructor
    private ActionFactory(){
    	actionPool = new HashMap<String, Action>();
    }

    public static ActionFactory getInstance(Presentation p){
    	presentation = p;
        return instance;
    }
    
    private Action createAction(String key) {
    	Action newAction = null;
    	switch (key) {
	    	case OPEN_FILE:
	    		newAction = new OpenFile(presentation);
	    		break;
	    	case SYSTEM_EXIT:
	    		newAction = new SystemExit();
	    		break;
	    	case GO_TO_SLIDE:
	    		newAction = new GoToSlide(presentation);
	    		break;
	    	case NEXT_SLIDE:
	    		newAction = new NextSlide(presentation);
	    		break;
	    	case PREVIOUS_SLIDE:
	    		newAction = new PreviousSlide(presentation);
	    		break;
	    	case FIRST_SLIDE:
	    		newAction = new FirstSlide(presentation);
	    		break;
	    	case LAST_SLIDE:
	    		newAction = new LastSlide(presentation);
	    		break;
	    	case BEEP:
	    		newAction = new Beep();
	    		break;
	    	case NEW_FILE:
	    		newAction = new NewFile(presentation);
	    		break;
	    	case SAVE_FILE:
	    		newAction = new SaveFile(presentation);
	    		break;
	    	case ABOUT:
	    		newAction = new About();
	    		break;
	    	default: 
	    		new JabberPointException(null, ACTION_ERROR, ACTION_ERROR_MESSAGE);	    	
	    }
    	if (newAction != null) {
    		actionPool.put(key, newAction);
    	}
    	return newAction;
    }
    
    public Action getAction(String key) {
    	key = key.toLowerCase();
    	if (actionPool.containsKey(key)) {
    		return actionPool.get(key);
    	} else {
    		return createAction(key);
    	}
    }
    
    public Action getAction(String key, List<Action> actions) {
    	key = key.toLowerCase();
    	if (actionPool.containsKey(key)) {
    		return actionPool.get(key);
    	} else {
    		return createCompositeAction(key, actions);
    	}
    }
    
    private CompositeAction createCompositeAction(String actionKey, List<Action> actions) {
    	CompositeAction ca = new CompositeAction(actionKey);
    	for (int i = 0; i < actions.size(); i++) {
    		ca.addAction(actions.get(i));
    	}
    	actionPool.put(actionKey, ca);
    	return ca;    	
    }

    public static void clearActionPool() {
    	actionPool.clear();
    }
}
