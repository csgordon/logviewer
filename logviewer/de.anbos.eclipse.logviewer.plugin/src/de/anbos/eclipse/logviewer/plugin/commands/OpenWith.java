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

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.anbos.eclipse.logviewer.plugin.EditorPropertyTester;
import guitypes.checkers.quals.*;
@UI public class OpenWith implements @UI IHandler { /* Colin Gordon: anno only b/c of the postDirectSupertypes() behavior. */
	
        @SafeEffect /* Colin Gordon: anno only b/c of the postDirectSupertypes() behavior. */
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub
	}

        @SafeEffect /* Colin Gordon: anno only b/c of the postDirectSupertypes() behavior. */
	public void dispose() {
		// TODO Auto-generated method stub
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart part = HandlerUtil.getActivePart(event);
		IObjectActionDelegate action = EditorPropertyTester.hasResourceSelection(part);
		if (action == null) {
			action = EditorPropertyTester.hasAbstractConsole(part);
		}
		if (action != null)
			action.run(null);
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
