package fr.upmc.gaspardleo.step1.requestdispatcher.interfaces;

public interface RequestDispatcherI{

	public void registerVM(String vmUri) throws Exception;
	public void unregisterVM(String vmUri) throws Exception;
}