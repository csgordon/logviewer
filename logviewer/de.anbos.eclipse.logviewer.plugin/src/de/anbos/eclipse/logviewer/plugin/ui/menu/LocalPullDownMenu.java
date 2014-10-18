package de.anbos.eclipse.logviewer.plugin.ui.menu;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.widgets.Shell;

import de.anbos.eclipse.logviewer.plugin.LogViewer;
import de.anbos.eclipse.logviewer.plugin.action.ClearHistoryAction;
import de.anbos.eclipse.logviewer.plugin.action.HistoryFileOpenViewAction;
import de.anbos.eclipse.logviewer.plugin.preferences.FileHistoryTracker;
import de.anbos.eclipse.logviewer.plugin.preferences.HistoryFile;

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
public class LocalPullDownMenu implements IMenuListener {

	// Constant ----------------------------------------------------------------

	private static final String FILELIST	= "Filelist";
	private static final String SEPARATOR	= "Separator";

	// Attribute ---------------------------------------------------------------

	private IMenuManager menuManager;
	private LogViewer view;
	private Shell shell;
	private List<Object> actionList;

	// Constructor -------------------------------------------------------------

	public LocalPullDownMenu(IMenuManager menuManager, LogViewer view, Shell shell) {
		this.menuManager = menuManager;
		this.view = view;
		this.shell = shell;
		actionList = new Vector<Object>();
		this.menuManager.setRemoveAllWhenShown(true);
		this.menuManager.addMenuListener(this);
	}

	// Public ------------------------------------------------------------------

	public void addAction(IAction action) {
		actionList.add(action);
	}

	public void addSeparator() {
		actionList.add(SEPARATOR);
	}

	public void addFilelist() {
		actionList.add(FILELIST);
	}

	public void finalize() {
		fillMenu(); // Colin Gordon: BUG: finalizers run on their own thread!  Even if fillMenu() doesn't really need to be UI, this is at least bizarre
	}

	public void menuAboutToShow(IMenuManager manager) {
		fillMenu();
	}

	// Private -----------------------------------------------------------------

	private void fillMenu() {
		boolean historyIsEmpty = true;
		Iterator<Object> it = actionList.iterator();
		while(it.hasNext()) {
			Object object = it.next();
			if(object instanceof String) {
				String value = (String)object;
				if(value.equals(SEPARATOR)) {
					menuManager.add(new Separator());
				}
				if(value.equals(FILELIST)) {
					List<?> files = FileHistoryTracker.getInstance().getFiles();
					for(int i = 0 ; i < files.size() ; i++) {
						HistoryFile file = (HistoryFile)files.get(i);
						if(file != null) {
							HistoryFileOpenViewAction action = new HistoryFileOpenViewAction(file,view,shell);
							menuManager.add(action);
							historyIsEmpty = false;
						}
					}
				}
				continue;
			} else if(object instanceof ClearHistoryAction) {
				((ClearHistoryAction)object).setEnabled(!historyIsEmpty);
			}
			menuManager.add((IAction)object);
		}
	}
}
