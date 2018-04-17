package actions;

import java.util.ArrayList;

/**
 * @author peter
 *
 */
public abstract class CompositeAction implements Action {
	
	ArrayList<Action> actions;
	
	public CompositeAction() {
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
}
