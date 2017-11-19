package fr.upmc.gaspardleo.requestgenerator;

import java.util.HashMap;
import java.util.Map;

import fr.upmc.components.ports.AbstractPort;
import fr.upmc.datacenter.software.interfaces.RequestI;
import fr.upmc.gaspardleo.requestgenerator.interfaces.RequestGeneratorI;
import fr.upmc.gaspardleo.requestgenerator.ports.RequestGeneratorInboundPort;
//import fr.upmc.datacenter.software.interfaces.RequestNotificationHandlerI;
//import fr.upmc.gaspardleo.requestdispatcher.ports.RequestNotificationHandlerInboundPort;

public class RequestGenerator 
	extends fr.upmc.datacenterclient.requestgenerator.RequestGenerator{

	public static enum	RGPortTypes {
		INTROSPECTION, MANAGEMENT_IN, REQUEST_SUBMISSION_OUT, REQUEST_NOTIFICATION_IN, REQUEST_NOTIFICATION_HANDLER_IN
	}
	
	private static double MEAN_INTER_ARRIVAL_TIME 	= 500.0;
	private static long MEAN_NUMBER_OF_INSTRUCTIONS = 6000000000L;
	
	private static String rg_rgmip 	= AbstractPort.generatePortURI();
	private static String rg_rsop 	= AbstractPort.generatePortURI();
	private static String rg_rnip 	= AbstractPort.generatePortURI();
	
	private String rgURI;
	
	private RequestGeneratorInboundPort inPort;
	
	//private RequestNotificationHandlerInboundPort 		rnhip;	
	
	public RequestGenerator(
			String rgURI) throws Exception {
		
		super(
			rgURI, 
			MEAN_INTER_ARRIVAL_TIME, 
			MEAN_NUMBER_OF_INSTRUCTIONS, 
			rg_rgmip,
			rg_rsop,
			rg_rnip);
		
		this.rgURI = rgURI;
		
		this.addRequiredInterface(RequestGeneratorI.class);
		this.inPort = new RequestGeneratorInboundPort(rgURI, this);
		this.addPort(inPort);
		this.inPort.publishPort();
		
//		System.out.println("[DEBUG LEO] rnhip ...");
//		
//		this.rnhip = new RequestNotificationHandlerInboundPort(this);
//		this.addPort(rnhip);
//		this.rnhip.publishPort();
//		
//		System.out.println("[DEBUG LEO] rnhip ok");
//		
//		this.addRequiredInterface(RequestNotificationHandlerI.class);
//		
//		System.out.println("[DEBUG LEO] add");
		
		// Rg debug
		this.toggleTracing();
		this.toggleLogging();
	}

	@Override
	public void acceptRequestTerminationNotification(RequestI r) throws Exception {
		super.logMessage(rgURI  + " : gettting an answer for " + r.getRequestURI());
		super.acceptRequestTerminationNotification(r);
	}
	
	public Map<RGPortTypes, String>	getRGPortsURI() throws Exception {
		HashMap<RGPortTypes, String> ret =
				new HashMap<RGPortTypes, String>();		
		ret.put(RGPortTypes.INTROSPECTION,
				this.rgURI);
		ret.put(RGPortTypes.MANAGEMENT_IN,
				RequestGenerator.rg_rgmip) ;
		ret.put(RGPortTypes.REQUEST_SUBMISSION_OUT,
				RequestGenerator.rg_rsop);
		ret.put(RGPortTypes.REQUEST_NOTIFICATION_IN,
				RequestGenerator.rg_rnip);
//		ret.put(RGPortTypes.REQUEST_NOTIFICATION_HANDLER_IN,
//				this.rnhip.getPortURI());
		return ret ;
	}
	
}
