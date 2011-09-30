package org.snow.action;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractAsyncAction implements AsyncAction {

	private final List<Action> updateCallbackList;

	private final List<Action> completeCallbackList;

	protected AbstractAsyncAction() {
		updateCallbackList = new LinkedList<Action>();
		completeCallbackList = new LinkedList<Action>();
	}

	public void run() {
		execute();

		for( Action callback : completeCallbackList )
			callback.execute();
	}

	public void addUpdateCallback( final Action callback ) {
		updateCallbackList.add( callback );
	}

	public void addCompleteCallback( final Action callback ) {
		completeCallbackList.add( callback );
	}

	protected List<Action> getUpdateCallbackList() {
		return updateCallbackList;
	}

	protected List<Action> getCompleteCallbackList() {
		return completeCallbackList;
	}

}
