package actions;

import java.util.HashMap;
import java.util.List;

import model.Presentation;

/**
 * @author peter
 *
 */
public class ActionFactory {
	public static final String OPEN_FILE = "openfile";
	public static final String SYSTEM_EXIT = "systemexit";
	public static final String GO_TO_SLIDE = "gotoslide";
	public static final String NEXT_SLIDE = "nextslide";
	public static final String PREVIOUS_SLIDE = "previousslide";
	public static final String FIRST_SLIDE = "firstslide";
	public static final String LAST_SLIDE = "lastslide";
	public static final String BEEP = "beep";
	
	private static Presentation presentation;
	
    private static final ActionFactory instance = new ActionFactory(presentation);
    private HashMap<String, Action> actionPool;
    
    //private constructor to avoid client applications to use constructor
    private ActionFactory(Presentation p){
    	actionPool = new HashMap<String, Action>();
    	presentation = p;
    }

    public static ActionFactory getInstance(Presentation p){
    	presentation = p;
        return instance;
    }
    
    private Action createAction(String key) {
    	Action newAction = null;
    	switch (key) {
	    	case OPEN_FILE:	{
	    		newAction = new OpenFile(presentation);
	    		break;
	    	}
	    	case SYSTEM_EXIT:	{
	    		newAction = new SystemExit();
	    		break;
	    	}
	    	case GO_TO_SLIDE: {
	    		newAction = new GoToSlide(presentation);
	    		break;
	    	}
	    	case NEXT_SLIDE: {
	    		newAction = new NextSlide(presentation);
	    		break;
	    	}
	    	case PREVIOUS_SLIDE: {
	    		newAction = new PreviousSlide(presentation);
	    		break;
	    	}
	    	case FIRST_SLIDE: {
	    		newAction = new FirstSlide(presentation);
	    		break;
	    	}
	    	case LAST_SLIDE: {
	    		newAction = new LastSlide(presentation);
	    		break;
	    	}
	    	case BEEP: {
	    		newAction = new Beep();
	    		break;
	    	}
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
    		return createAction(key, actions);
    	}
    }
    
    private Action createAction(String actionKey, List<Action> actions) {
    	CompositeAction ca = new CompositeAction(actionKey);
    	for (int i = 0; i < actions.size(); i++) {
    		ca.addAction(actions.get(i));
    	}
    	actionPool.put(actionKey, ca);
    	return ca;    	
    }
}
