package fr.upmc.datacenter.hardware.processors.interfaces;

//Copyright Jacques Malenfant, Univ. Pierre et Marie Curie.
//
//Jacques.Malenfant@lip6.fr
//
//This software is a computer program whose purpose is to provide a
//basic component programming model to program with components
//distributed applications in the Java programming language.
//
//This software is governed by the CeCILL-C license under French law and
//abiding by the rules of distribution of free software.  You can use,
//modify and/ or redistribute the software under the terms of the
//CeCILL-C license as circulated by CEA, CNRS and INRIA at the following
//URL "http://www.cecill.info".
//
//As a counterpart to the access to the source code and  rights to copy,
//modify and redistribute granted by the license, users are provided only
//with a limited warranty  and the software's author,  the holder of the
//economic rights,  and the successive licensors  have only  limited
//liability. 
//
//In this respect, the user's attention is drawn to the risks associated
//with loading,  using,  modifying and/or developing or reproducing the
//software by the user in light of its specific status of free software,
//that may mean  that it is complicated to manipulate,  and  that  also
//therefore means  that it is reserved for developers  and  experienced
//professionals having in-depth computer knowledge. Users are therefore
//encouraged to load and test the software's suitability as regards their
//requirements in conditions enabling the security of their systems and/or 
//data to be ensured and,  more generally, to use and operate it in the 
//same conditions as regards security. 
//
//The fact that you are presently reading this means that you have had
//knowledge of the CeCILL-C license and that you accept its terms.

import fr.upmc.components.interfaces.OfferedI;
import fr.upmc.components.interfaces.RequiredI;

/**
 * The interface <code>ProcessorIntrospectionI</code> gives access to the
 * state information of processors through offered ports of processors.
 *
 * <p><strong>Description</strong></p>
 * 
 * <p><strong>Invariant</strong></p>
 * 
 * <pre>
 * invariant		true
 * </pre>
 * 
 * <p>Created on : January 28, 2015</p>
 * 
 * @author	<a href="mailto:Jacques.Malenfant@lip6.fr">Jacques Malenfant</a>
 * @version	$Name$ -- $Revision$ -- $Date$
 */
public interface			ProcessorIntrospectionI
extends		OfferedI,
			RequiredI
{
	/**
	 * return the number of cores on the processor.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true			// no precondition.
	 * post	return > 0
	 * </pre>
	 *
	 * @return	the number of cores on the processor.
	 * @throws Exception
	 */
	public int			getNumberOfCores() throws Exception ;

	/**
	 * return the default frequency of the processor.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true			// no precondition.
	 * post	return > 0
	 * </pre>
	 *
	 * @return	the default frequency of the processor.
	 * @throws Exception
	 */
	public int			getDefaultFrequency() throws Exception ;

	/**
	 * return the maximum frequency gap tolerated on this processor.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true			// no precondition.
	 * post	return >= 0
	 * </pre>
	 *
	 * @return	the maximum frequency gap tolerated on this processor.
	 * @throws Exception
	 */
	public int			getMaxFrequencyGap() throws Exception ;

	/**
	 * return true if <code>coreNo</code> is a valid core number on this
	 * processor and false otherwise.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true			// no precondition.
	 * post	true			// no postcondition.
	 * </pre>
	 *
	 * @param coreNo	core number to be tested.
	 * @return			true if <code>coreNo</code> is a valid core number.
	 * @throws Exception
	 */
	public boolean		isValidCoreNo(final int coreNo) throws Exception ;

	/**
	 * return true if <code>frequency>/code> is an admissible frequency for a
	 * core on this processor, and false otherwise.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true			// no precondition.
	 * post	true			// no postcondition.
	 * </pre>
	 *
	 * @param frequency	frequency to be tested.
	 * @return			true if <code>frequency>/code> is admissible.
	 * @throws Exception
	 */
	public boolean		isAdmissibleFrequency(final int frequency)
	throws Exception ;

	/**
	 * return true if <code>frequency>/code> is a currently possible frequency
	 * for a given core on this processor, and false otherwise.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true			// no precondition.
	 * post	true			// no postcondition.
	 * </pre>
	 *
	 * @param coreNo	number of the core to be tested.
	 * @param frequency	frequency to be tested.
	 * @return			true if <code>frequency>/code> is currently possible.
	 * @throws Exception
	 */
	public boolean		isCurrentlyPossibleFrequencyForCore(
		int coreNo,
		int frequency
		) throws Exception ;

	/**
	 * return the static state of the processor as an instance of
	 * <code>ProcessorStaticStateI</code>.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true			// no precondition.
	 * post	return != null
	 * </pre>
	 *
	 * @return	the static state of the processor.
	 * @throws Exception
	 */
	public ProcessorStaticStateI	getStaticState() throws Exception ;

	/**
	 * return the dynamic state of the processor as an instance of
	 * <code>ProcessorDynamicStateI</code>.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true			// no precondition.
	 * post	return != null
	 * </pre>
	 *
	 * @return	the dynamic state of the processor.
	 * @throws Exception
	 */
	public ProcessorDynamicStateI	getDynamicState() throws Exception ;
}
