package fr.upmc.gaspardleo.computerpool.ports;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import fr.upmc.components.ComponentI;
import fr.upmc.components.ports.AbstractOutboundPort;
import fr.upmc.gaspardleo.applicationvm.ApplicationVM.ApplicationVMPortTypes;
import fr.upmc.gaspardleo.componentCreator.ComponentCreator;
import fr.upmc.gaspardleo.computerpool.ComputerPool;
import fr.upmc.gaspardleo.computerpool.interfaces.ComputerPoolI;

public class ComputerPoolInbounPort 
		extends AbstractOutboundPort
		implements ComputerPoolI {

	public ComputerPoolInbounPort(String uri, ComponentI owner) throws Exception {
		super(uri, ComputerPoolI.class, owner);
	}

	@Override
	public void createNewComputer(String computerURI,
			HashSet<Integer> possibleFrequencies,
			HashMap<Integer, Integer> processingPower,
			Integer defaultFrequency,
			Integer maxFrequencyGap,
			Integer numberOfProcessors,
			Integer numberOfCores, 
			ComponentCreator cc) throws Exception {
		
		final ComputerPool computerPool = (ComputerPool)this.owner;
		computerPool.handleRequestAsync(
				new ComponentI.ComponentService<ComputerPool>(){
					@Override
					public ComputerPool call() throws Exception {
						computerPool.createNewComputer(
								computerURI, 
								possibleFrequencies, 
								processingPower,
								defaultFrequency, 
								maxFrequencyGap, 
								numberOfProcessors, 
								numberOfCores, 
								cc);
						return computerPool;
					}});

	}

	@Override
	public Map<ApplicationVMPortTypes, String> createNewApplicationVM(
			String avmURI, 
			Integer numberOfCoreToAllocate, 
			ComponentCreator cc) throws Exception {
		
		final ComputerPool computerPool = (ComputerPool)this.owner;
		return computerPool.handleRequestSync(
				new ComponentI.ComponentService<Map<ApplicationVMPortTypes, String>>(){
					@Override
					public Map<ApplicationVMPortTypes, String> call() throws Exception {
						return computerPool.createNewApplicationVM(
								avmURI, 
								numberOfCoreToAllocate, 
								cc);
					}});
	}
}
