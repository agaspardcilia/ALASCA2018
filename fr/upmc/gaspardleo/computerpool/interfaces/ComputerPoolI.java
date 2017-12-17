package fr.upmc.gaspardleo.computerpool.interfaces;

import java.util.Map;
import java.util.Set;

import fr.upmc.components.interfaces.OfferedI;
import fr.upmc.components.interfaces.RequiredI;
import fr.upmc.gaspardleo.applicationvm.ApplicationVM;
import fr.upmc.gaspardleo.applicationvm.ApplicationVM.ApplicationVMPortTypes;
import fr.upmc.gaspardleo.computer.Computer;

public interface ComputerPoolI extends OfferedI, RequiredI {
	
	
	/**
	 * Ajoute un ordinateur au pool actuel du composant.
	 * Détails des paramètres disponibles à @see {@link fr.upmc.gaspardleo.computer.Computer}.
	 * @return
	 * 		Nouveau Computer.
	 */
	public void createNewComputer(String computerURI, Set<Integer> possibleFrequencies, Map<Integer, Integer> processingPower,
			int defaultFrequency, int maxFrequencyGap, int numberOfProcessors, int numberOfCores) throws Exception;
	
	/**
	 * Créé une nouvelle ApplicationVM.
	 * @param avmURI 
	 * 		URI de la nouvelle ApplicationVM.
	 * @param numberOfCoreToAllocate
	 * 		Nombre de coeur à allouer à l'ApplicationVM.
	 * @return
	 * 		La nouvelle ApplicationVM.
	 */
	public Map<ApplicationVMPortTypes, String> createNewApplicationVM(String avmURI, int numberOfCoreToAllocate) throws Exception;
}
