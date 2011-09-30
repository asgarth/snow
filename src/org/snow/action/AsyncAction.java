package org.snow.action;

public interface AsyncAction extends Action, Runnable {

	public void cancel();

	public void addUpdateCallback( Runnable callback );

	public void addCompleteCallback( Runnable callback );

}
