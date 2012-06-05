/*
 * Copyright 2009 - 2010 by Andre Bossert
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package de.anbos.eclipse.logviewer.plugin.commands;

import java.util.ResourceBundle;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.FindReplaceAction;

import de.anbos.eclipse.logviewer.plugin.LogViewer;
import de.anbos.eclipse.logviewer.plugin.LogViewerPlugin;
import guitypes.checkers.quals.*;

@UI public class FindReplaceHandler implements @UI IHandler { /* Colin Gordon: anno only b/c of the postDirectSupertypes() behavior. */

	@SafeEffect /* Colin Gordon: anno only b/c of the postDirectSupertypes() behavior. */
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	FindReplaceAction action = null;
	
	@SafeEffect /* Colin Gordon: anno only b/c of the postDirectSupertypes() behavior. */
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@UIEffect public Object execute(ExecutionEvent event) throws ExecutionException {
		ResourceBundle bundle = LogViewerPlugin.getDefault().getResourceBundle();
		if (action == null)
			action = new FindReplaceAction(bundle,"dialog.findreplace.",(LogViewer)HandlerUtil.getActivePart(event));
		action.run();
		return null;
	}

	@SafeEffect /* Colin Gordon: anno only b/c of the postDirectSupertypes() behavior. */
	public boolean isEnabled() {
		return true;
	}

	@SafeEffect /* Colin Gordon: anno only b/c of the postDirectSupertypes() behavior. */
	public boolean isHandled() {
		return true;
	}

	@SafeEffect /* Colin Gordon: anno only b/c of the postDirectSupertypes() behavior. */
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
