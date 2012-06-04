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

package de.anbos.eclipse.logviewer.plugin.action.delegate;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import de.anbos.eclipse.logviewer.plugin.ILogViewerConstants;
import de.anbos.eclipse.logviewer.plugin.LogViewer;
import de.anbos.eclipse.logviewer.plugin.LogViewerPlugin;
import de.anbos.eclipse.logviewer.plugin.LogFile.LogFileType;
import de.anbos.eclipse.logviewer.plugin.preferences.FileHistoryTracker;
import de.anbos.eclipse.logviewer.plugin.preferences.HistoryFile;

public class FileOpenViewActionDelegate implements ILogViewerActionDelegate {

	private String parentPath = null;
	private boolean fileOpened = false;
	private LogFileType type;

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public boolean isFileOpened() {
		return fileOpened;
	}
	
	/* (non-Javadoc)
	 * @see de.anbos.eclipse.logviewer.plugin.action.ILogfileAction#run(de.anbos.eclipse.logviewer.plugin.LogViewer, org.eclipse.swt.widgets.Shell)
	 */
	public void run(LogViewer view, Shell shell) {

		fileOpened = false;
		
		// log file type
		String typeStr = null;
		type = LogFileType.LOGFILE_SYSTEM_FILE;

		/*
		String conStr = "Console: ";
	    LogFileTypeDialog typeDialog = new LogFileTypeDialog(shell);
	    typeDialog.setBlockOnOpen(true);
		int retval = typeDialog.open();
		if(retval == EncodingDialog.OK) {
			typeStr = typeDialog.getValue();
			if (typeStr.indexOf(conStr) == 0) {
				type = LogFileType.LOGFILE_ECLIPSE_CONSOLE;
				typeStr = typeStr.substring(conStr.length());
			}
		} else {
			return;
		}
		*/

		if (type == LogFileType.LOGFILE_SYSTEM_FILE) {
		    // load filter extensions		    
			String filterExtensions = LogViewerPlugin.getDefault().getPreferenceStore().getString(ILogViewerConstants.PREF_FILTER_EXTENSIONS);
			// opening file(s) in log view
		    FileDialog dialog = new FileDialog(shell,SWT.OPEN|SWT.MULTI);
		    String[] extensions = {
		    		filterExtensions,
		    		"*.*"
		    };

	    	//
		    if (parentPath == null) {
		    	Object[] file_list = FileHistoryTracker.getInstance().getFiles().toArray();
		    	if (file_list.length >= 1)
		    	{
		    		HistoryFile history_file = (HistoryFile)(file_list[file_list.length - 1]);
		    		File file = new File(history_file.getPath());
		    		if (file.isDirectory()) {
		    			parentPath = file.toString();
		    		} else {
		    			parentPath = file.getParent();
		    		}
		    	}
		    }
		    dialog.setFilterPath(parentPath);
		    dialog.setFilterExtensions(extensions);
		    dialog.setFilterIndex(0);
		    String path = dialog.open();
		    if (path != null) {
		    	File tempFile = new File(path);
		    	path = tempFile.isDirectory() ? tempFile.toString() : tempFile.getParent();
		    	String selectedFiles[] = dialog.getFileNames();
		    	for (int i=0;i<selectedFiles.length;i++) {
		    		String fileStr = path.endsWith(File.separator) ? path + selectedFiles[i] : path + File.separator + selectedFiles[i];
		    		if (!view.checkAndOpenFile(type,fileStr, true))
		    	        fileOpened = true;
		    	}
		    }
		} else if (type == LogFileType.LOGFILE_ECLIPSE_CONSOLE) {
    		if (!view.checkAndOpenFile(type, typeStr, true))
    	        fileOpened = true;			
		}
	}
}
