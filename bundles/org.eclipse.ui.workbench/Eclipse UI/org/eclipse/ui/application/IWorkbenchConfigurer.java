/*******************************************************************************
 * Copyright (c) 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.application;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.window.WindowManager;
import org.eclipse.ui.AboutInfo;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.WorkbenchException;

/**
 * Interface providing special access for configuring the workbench.
 * <p>
 * Note that these objects are only available to the main application
 * (the plug-in that creates and owns the workbench).
 * </p>
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * 
 * @see WorkbenchAdviser#initialize
 * @since 3.0
 */
public interface IWorkbenchConfigurer {
	
	/**
	 * Returns the underlying workbench.
	 * 
	 * @return the workbench
	 */
	public IWorkbench getWorkbench();

	/**
	 * Returns the configuration information found in the
	 * <code>about.ini</code> file for the primary feature.
	 * Fails if the <code>about.ini</code> file cannot be opened
	 * and parsed correctly.
	 * 
	 * @return the confirguration information for the primary feature
	 */
	public AboutInfo getPrimaryFeatureAboutInfo() throws WorkbenchException;
	
	/**
	 * Returns whether the workbench state should be saved on close and 
	 * restored on subsequence open.
	 * 
	 * @return <code>true</code> to save and restore workbench state, or
	 * 	<code>false</code> to forget current workbench state on close.
	 */
	public boolean getSaveAndRestore();

	/**
	 * Sets whether the workbench state should be saved on close and 
	 * restored on subsequence open.
	 * 
	 * @param enabled <code>true</code> to save and restore workbench state, or
	 * 	<code>false</code> to forget current workbench state on close.
	 */	
	public void setSaveAndRestore(boolean enabled);
	
	/**
	 * Returns the workbench window manager.
	 *
	 * @return the workbench window manager
	 */
	public WindowManager getWorkbenchWindowManager();
	
	/**
	 * Returns the workbench image registry. This image registry holds
	 * image descriptors for various images that appear in the workbench.
	 * <p>
	 * Unlike {@link org.eclipse.ui.ISharedImages ISharedImages}, this object
	 * gives one full read-write access.
	 * </p>
	 *
	 * @return the workbench image registry
	 * @see org.eclipse.ui.ISharedImages
	 */
	public ImageRegistry getWorkbenchImageRegistry();

	/**
	 * Forces the workbench to close due to an emergency. This method should
	 * only be called when the workbench is in dire straights and cannot
	 * continue, and cannot even risk a normal workbench close (think "out of
	 * memory" or "unable to create shell"). When this method is called, an
	 * abbreviated workbench shutdown sequence is performed (less critical
	 * steps may be skipped). The workbench adviser is still called; however,
	 * it must not attempt to communicate with the user. While an emergency
	 * close is in progress, <code>emergencyClosing</code> returns
	 * <code>true</code>. Workbench adviser methods should always check this
	 * flag before communicating with the user.
	 * 
	 * @see #emergencyClosing
	 */
	public void emergencyClose();

	/**
	 * Returns whether the workbench is being closed due to an emergency.
	 * When this method returns <code>true</code>, the workbench is in dire
	 * straights and cannot continue. Indeed, things are so bad that we cannot
	 * even risk a normal workbench close. Workbench adviser methods should
	 * always check this flag before attempting to communicate with the user.
	 * 
	 * @return <code>true</code> if the workbench is in the process of being
	 * closed under emergency conditions, and <code>false</code> otherwise
	 */
	public boolean emergencyClosing();

	/**
	 * Returns an object that can be used to configure the given window.
	 * 
	 * @param window a workbench window
	 * @return a workbench window configurer
	 */
	public IWorkbenchWindowConfigurer getWindowConfigurer(IWorkbenchWindow window);

	/**
	 * Returns the data associated with the workbench at the given key.
	 * 
	 * @param key the key
	 * @return the data, or <code>null</code> if there is no data at the given
	 * key
	 */
	public Object getData(String key);
	
	/**
	 * Sets the data associated with the workbench at the given key.
	 * 
	 * @param key the key
	 * @param data the data, or <code>null</code> to delete existing data
	 */
	public void setData(String key, Object data);
}
