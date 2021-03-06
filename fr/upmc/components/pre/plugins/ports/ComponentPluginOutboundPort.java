package fr.upmc.components.pre.plugins.ports;

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
import fr.upmc.components.ports.AbstractOutboundPort;
import fr.upmc.components.pre.plugins.PluginI;
import fr.upmc.components.pre.plugins.interfaces.ComponentPluginI;

/**
 * The class <code>ComponentPluginOutboundPort</code>
 *
 * <p><strong>Description</strong></p>
 * 
 * <p><strong>Invariant</strong></p>
 * 
 * <pre>
 * invariant	true
 * </pre>
 * 
 * <p>Created on : February 18, 2016</p>
 * 
 * @author	<a href="mailto:Jacques.Malenfant@lip6.fr">Jacques Malenfant</a>
 * @version	$Name$ -- $Revision$ -- $Date$
 */
public class			ComponentPluginOutboundPort
extends		AbstractOutboundPort
implements	ComponentPluginI
{
	public				ComponentPluginOutboundPort(
		String uri,
		ComponentI owner
		) throws Exception
	{
		super(uri, ComponentPluginI.class, owner);
	}

	public				ComponentPluginOutboundPort(
		ComponentI owner
		) throws Exception
	{
		super(ComponentPluginI.class, owner);
	}

	/**
	 * 
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	ComponentPluginI.class.isAssignableFrom(implementedInterface)
	 * post	true			// no postcondition.
	 * </pre>
	 *
	 * @param implementedInterface
	 * @param owner
	 * @throws Exception
	 */
	public				ComponentPluginOutboundPort(
		Class<?> implementedInterface,
		ComponentI owner
		) throws Exception
	{
		super(implementedInterface, owner) ;

		assert	ComponentPluginI.class.isAssignableFrom(implementedInterface) ;
	}

	/**
	 * 
	 * 
	 * <p><strong>Contract</strong></p>
	 * 
	 * <pre>
	 * pre	ComponentPluginI.class.isAssignableFrom(implementedInterface)
	 * post	true			// no postcondition.
	 * </pre>
	 *
	 * @param uri
	 * @param implementedInterface
	 * @param owner
	 * @throws Exception
	 */
	public				ComponentPluginOutboundPort(
		String uri,
		Class<?> implementedInterface,
		ComponentI owner
		) throws Exception
	{
		super(uri, implementedInterface, owner) ;

		assert	ComponentPluginI.class.isAssignableFrom(implementedInterface) ;
	}

	/**
	 * @see fr.upmc.components.pre.plugins.interfaces.ComponentPluginI#install(fr.upmc.components.pre.plugins.PluginI)
	 */
	@Override
	public void			install(PluginI plugin)
	throws Exception
	{
		((ComponentPluginI)this.connector).install(plugin) ;
	}

	/**
	 * @see fr.upmc.components.pre.plugins.interfaces.ComponentPluginI#isInitialised(java.lang.String)
	 */
	@Override
	public boolean		isInitialised(String pluginId) throws Exception
	{
		return ((ComponentPluginI)this.connector).isInitialised(pluginId) ;
	}

	/**
	 * @see fr.upmc.components.pre.plugins.interfaces.ComponentPluginI#uninstall(java.lang.String)
	 */
	@Override
	public void			uninstall(String pluginId) throws Exception
	{
		((ComponentPluginI)this.connector).uninstall(pluginId) ;
	}
}
