package fr.upmc.gaspardleo.test;

import java.util.ArrayList;
import java.util.List;

import fr.upmc.components.AbstractComponent;
import fr.upmc.components.ports.AbstractPort;
import fr.upmc.datacenter.software.connectors.RequestSubmissionConnector;
import fr.upmc.datacenterclient.requestgenerator.connectors.RequestGeneratorManagementConnector;
import fr.upmc.datacenterclient.requestgenerator.ports.RequestGeneratorManagementOutboundPort;
import fr.upmc.gaspardleo.admissioncontroller.AdmissionController;
import fr.upmc.gaspardleo.cvm.CVM;
import fr.upmc.gaspardleo.cvm.CVMComponent;
import fr.upmc.gaspardleo.cvm.CVMComponent.CVMPortTypes;
import fr.upmc.gaspardleo.requestgenerator.RequestGenerator;
import fr.upmc.gaspardleo.requestgenerator.RequestGenerator.RGPortTypes;

public class Test {
	
	private final static int 	NB_DATASOURCE = 10;
	
	private CVMComponent 		cvmc;
	private CVM 				cvm;
	private AdmissionController	ac;

	private List<RequestGeneratorManagementOutboundPort> rgmops;

	public Test(){
		rgmops = new ArrayList<>();
		initTest();
	}

	private void initTest(){
		try {	

			// CVM creation
			this.cvm 	= new CVM();

			// CVM Component creation
			this.cvmc 	= new CVMComponent(cvm);

			// Admission Controller creation
			this.ac = new AdmissionController();
			
			// Simply adds some request generators to the current admission controller.
			for (int i = 0; i < NB_DATASOURCE; i++) {
				this.addDataSource(i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void addDataSource(int i) throws Exception {
						
		// Request Generator creation
		RequestGenerator rg  = createRequestGenerator("rg-"+i);

		// Dynamic ressources creation
		String rd_rsip = this.ac.addRequestSource(
			"rd-"+i,
			rg.getRGPortsURI().get(RGPortTypes.REQUEST_NOTIFICATION_IN),
			this.cvmc.getCVMPortsURI().get(CVMPortTypes.CVM_IN));
		
		// Port connections
		rg.doPortConnection(
			rg.getRGPortsURI().get(RGPortTypes.REQUEST_SUBMISSION_OUT),
			rd_rsip,
			RequestSubmissionConnector.class.getCanonicalName());
	}
	
	private RequestGenerator createRequestGenerator(String RG_URI) throws Exception{
		
		// Request Generator creation
		RequestGenerator rg  = new RequestGenerator(RG_URI);

		// Rg debug
		rg.toggleTracing();
		rg.toggleLogging();

		// Components deployment
		this.cvm.deploy();
		this.cvm.deployComponent(rg);
		
		createRGManagement(rg);
		
		return rg;
	}
	
	private void createRGManagement(RequestGenerator rg) throws Exception{
		
		// Rg management creation
		RequestGeneratorManagementOutboundPort rgmop = new RequestGeneratorManagementOutboundPort(
			AbstractPort.generatePortURI(),
			new AbstractComponent(0, 0) {});

		rgmop.publishPort();
		
		rgmop.doConnection(
			rg.getRGPortsURI().get(RGPortTypes.MANAGEMENT_IN),
			RequestGeneratorManagementConnector.class.getCanonicalName());
		
		this.rgmops.add(rgmop);
	}

	//TODO proposer un scénario qui permet de mettre en évidence le refus de requêtes
	
	public void testScenario() throws Exception {

		// start the request generation in the request generator.
		this.rgmops.forEach(rgmop -> {
			try {
				rgmop.startGeneration();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		
		// wait 20 seconds
		Thread.sleep(20000L);
		// then stop the generation.

		this.rgmops.forEach(rgmop -> {
			try {
				rgmop.stopGeneration();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	public CVM getCvm() {
		return cvm;
	}
	
	public CVMComponent getCvmc() {
		return cvmc;
	}
	
	public static void main(String[] args){

		// AbstractCVM.toggleDebugMode() ;
		try {
			final Test tvmc = new Test() ;
			// Deploy the components
			tvmc.getCvm().deploy() ;

			System.out.println("starting...") ;
			// Start them.
			tvmc.getCvm().start() ;

			// Execute the chosen request generation test scenario in a
			// separate thread.
			new Thread(() -> {
				try {
					tvmc.testScenario();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}).start();;

			// Sleep to let the test scenario execute to completion.
			Thread.sleep(90000L) ;
			// Shut down the application.
			System.out.println("shutting down...") ;
			tvmc.getCvm().shutdown() ;
			System.out.println("ending...") ;
			// Exit from Java.
			System.exit(0) ;
		} catch (Exception e) {
			throw new RuntimeException(e) ;
		}
	}
}