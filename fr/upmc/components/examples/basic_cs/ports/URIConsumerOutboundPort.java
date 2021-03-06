package fr.upmc.components.examples.basic_cs.ports;

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

import fr.upmc.components.ComponentI;
import fr.upmc.components.examples.basic_cs.interfaces.URIConsumerI;
import fr.upmc.components.ports.AbstractOutboundPort;

/**
 * The class <code>URIConsumerOutboundPort</code> implements the outbound port
 * of a component that requires an URI service through the
 * <code>URIConsumerI</code> interface.
 *
 * <p><strong>Invariant</strong></p>
 * 
 * <pre>
 * invariant	true
 * </pre>
 * 
 * <p>Created on : January 22, 2014</p>
 * 
 * @author	<a href="mailto:Jacques.Malenfant@lip6.fr">Jacques Malenfant</a>
 * @version	$Name$ -- $Revision$ -- $Date$
 */
public class			URIConsumerOutboundPort
extends		AbstractOutboundPort
implements	URIConsumerI
{
	/**
	 * create the port with the given URI and the given owner.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true			// no precondition.
	 * post	true			// no postcondition.
	 * </pre>
	 *
	 * @param uri	URI of the port.
	 * @param owner	owner of the port.
	 */
	public				URIConsumerOutboundPort(
		String uri,
		ComponentI owner
		) throws Exception
	{
		super(uri, URIConsumerI.class, owner) ;
	}

	/**
	 * get an URI by calling the server component through the connector that
	 * implements the required interface.
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	true				// no more preconditions.
	 * post	true				// no more postconditions.
	 * </pre>
	 * 
	 * @see fr.upmc.components.examples.basic_cs.interfaces.URIConsumerI#getURI()
	 */
	@Override
	public String		getURI() throws Exception
	{
		return ((URIConsumerI)this.connector).getURI() ;
	}

	/**
	 * @see fr.upmc.components.examples.basic_cs.interfaces.URIConsumerI#getURIs(int)
	 */
	@Override
	public String[]		getURIs(int numberOfURIs) throws Exception
	{
		return ((URIConsumerI)this.connector).getURIs(numberOfURIs) ;
	}
}
