package de.anbos.eclipse.logviewer.plugin.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

import de.anbos.eclipse.logviewer.plugin.LogViewer;
import de.anbos.eclipse.logviewer.plugin.LogViewerPlugin;
import de.anbos.eclipse.logviewer.plugin.Logger;
import de.anbos.eclipse.logviewer.plugin.action.delegate.ILogViewerActionDelegate;

/*
 * Copyright (c) 2007 - 2011 by Michael Mimo Moratti
 * Licensed under the Apache License, Version 2.0 (the &quot;License&quot;);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */

import org.checkerframework.checker.guieffect.qual.*;
@UIType
public abstract class AbstractWorkbenchAction implements IWorkbenchWindowActionDelegate {

	// Attribute --------------------------------------------------------------------
    
	protected Logger logger; 
    
	private IWorkbenchWindow window;
	private ILogViewerActionDelegate actionDelegate;
	
	// Constructor ------------------------------------------------------------------
	
	public AbstractWorkbenchAction(ILogViewerActionDelegate actionDelegate) {
	    logger = LogViewerPlugin.getDefault().getLogger();
		this.actionDelegate = actionDelegate;
	}
	
	// Public -----------------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		if(window == null) {
            logger.logError("the current window is null, we therefore don't apply any action"); //$NON-NLS-1$
			return;
		}
		Shell shell = window.getShell();
		String viewId = "de.anbos.eclipse.logviewer.plugin.LogViewer";
		IViewPart view = null;
		try {
			view = window.getActivePage().showView(viewId);
		} catch(PartInitException pie) {
            logger.logError(pie);
			return;
		}
		
		if(!(view instanceof LogViewer) | shell == null) {
			logger.logError("unable to get current shell or log file view for that matter"); //$NON-NLS-1$
			return;
		}
		// call abstract run
		actionDelegate.run((LogViewer)view,shell);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}
}
