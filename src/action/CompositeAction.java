package action;

import java.util.ArrayList;
import java.util.List;

import main.JabberPointException;

/**
 * @author peter
 *
 */
public class CompositeAction implements Action {
	private static final String CAE = "Composite Action Error"; 
	private static final String CAE_MESSAGE = "Composite Actions interrupted"; 
	
	private ArrayList<Action> actions;
	private String key;
	private int delay;
	
	protected CompositeAction(String key) {
		this.key = key;
		actions = new ArrayList<Action>();
		delay = 500;
	}
	
	public void performAction() {
		actions.forEach(action-> {
			action.performAction();
	        try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				new JabberPointException(e, CAE, CAE_MESSAGE);
			}
		});
	}
	
	/** Add a single action to a composite action */
	public void addAction(Action action) {
		actions.add(action);
	}
	
	/** Remove the first occurrence of a single action from a composite action */
	public void removeAction(Action action) {
		actions.remove(action);
	}
	
	/** get a single action by index */
	public Action getAction(int index) {
		return actions.get(index);
	}

	/** get a single action by index */
	public List<Action> getActions() {
		return actions;
	}

	public List<Action> getActionsByKey(String actionKey) {
        List<Action> result = new ArrayList<>();
        actions.forEach(action -> {
            if (action.getKey() == actionKey) {
                result.add(action);
            }
            try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				new JabberPointException(e, CAE, CAE_MESSAGE);
			}
        });
        return result;
	}
	
	/** return the key */
	public String getKey() {
		return key;
	}
	
	/**
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * @param delay the delay to set
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}

}
