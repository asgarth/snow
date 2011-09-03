package org.snow.action;



public interface BackgroundAction extends Action {

	public void cancel();

	public void addUpdateCallback( Runnable callback );

	public void addCompleteCallback( Runnable callback );

}
