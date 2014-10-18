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

package de.anbos.eclipse.logviewer.plugin;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleView;
import org.checkerframework.checker.guieffect.qual.*;

@UIType public class ConsoleFactory implements IConsoleFactory {

	public void openConsole() {
		LogViewer logView = null;

		// get log view
		try {
			logView = (LogViewer) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("de.anbos.eclipse.logviewer.plugin.LogViewer");
		} catch (PartInitException e) {
			e.printStackTrace();
		}

		// get console view
		IConsoleView consoleView = null;
		try {
			consoleView = (IConsoleView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(IConsoleConstants.ID_CONSOLE_VIEW);
		} catch (PartInitException e) {
			e.printStackTrace();
		}

		if (logView != null && consoleView != null) {
			// show it
			consoleView.display(logView.getConsole());
			//logView.printDefaultMessage();
		}
	}

}
