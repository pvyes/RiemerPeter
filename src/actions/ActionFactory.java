package actions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author peter
 *
 */
public class ActionFactory {
	public static final String OPEN_FILE = "openfile";
	public static final String GO_TO_SLIDE = "gotoslide";
	public static final String OPEN_FILE_AND_GO_TO_SLIDE = "openfileandgotoslide";
	
    private static final ActionFactory instance = new ActionFactory();
    private Map<String, Action> actionPool;
    
    //private constructor to avoid client applications to use constructor
    private ActionFactory(){
    	actionPool = new HashMap<String, Action>();
    }

    public static ActionFactory getInstance(){
        return instance;
    }
    
    private Action createAction(String key) {
    	Action newAction = null;
    	switch (key) {
	    	case OPEN_FILE:	{
	    		newAction = new OpenFile();
	    		break;
	    	}
	    	case GO_TO_SLIDE: {
	    		newAction = new GoToSlide();
	    		break;
	    	}
	    	case OPEN_FILE_AND_GO_TO_SLIDE: {
	    		newAction = new OpenFileAndGoToSlide();
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

}
