package actions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peter
 *
 */
public class CompositeAction implements Action {
	
	private ArrayList<Action> actions;
	protected String key;
	
	public CompositeAction(String key) {
		this.key = key;
		actions = new ArrayList<Action>();
	}
	
	public void performAction() {
		actions.forEach(action->action.performAction());
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

	public List<Action> getActionsByKey(String actionKey) {
        List<Action> result = new ArrayList<>();
        actions.forEach(action -> {
            if (action.getKey() == actionKey) {
                result.add(action);
            }
        });
        return result;
	}
	
	/** return the key */
	public String getKey() {
		return key;
	} 
}
