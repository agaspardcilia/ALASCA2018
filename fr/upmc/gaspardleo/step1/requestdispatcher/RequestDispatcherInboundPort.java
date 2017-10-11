package fr.upmc.gaspardleo.step1.requestdispatcher;

import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractInboundPort;

public class RequestDispatcherInboundPort 
	extends AbstractInboundPort 
	implements RequestDispatcherI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public RequestDispatcherInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, RequestDispatcherI.class, owner);
	}

	@Override
	public void registerVM(String vmUri) throws Exception {
		final RequestDispatcher requestDispatcher = (RequestDispatcher)this.owner;
		requestDispatcher.handleRequestSync(
				new ComponentI.ComponentService<RequestDispatcher>(){
					@Override
					public RequestDispatcher call() throws Exception {
						requestDispatcher.registerVM(vmUri);
						return requestDispatcher;
					}});		
	}

	@Override
	public void unregisterVM(String vmUri) throws Exception {
		final RequestDispatcher requestDispatcher = (RequestDispatcher)this.owner;
		requestDispatcher.handleRequestSync(
				new ComponentI.ComponentService<RequestDispatcher>(){
					@Override
					public RequestDispatcher call() throws Exception {
						requestDispatcher.unregisterVM(vmUri);
						return requestDispatcher;
					}});
	}

}
