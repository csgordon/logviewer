package de.anbos.eclipse.logviewer.plugin.preferences;

import de.anbos.eclipse.logviewer.plugin.LogFile.LogFileType;

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

public class HistoryFile {

	// Attribute ---------------------------------------------------------------
	
	private String path;
	private LogFileType type;
	private int count;

	// Constructor -------------------------------------------------------------
	
	public HistoryFile(String path, LogFileType type, int count) {
		this.path = path;
		this.type = type;
		this.count = count;
	}
	
	// Public ------------------------------------------------------------------
	
	public String getPath() {
		return path;
	}

	public LogFileType getType() {
		return type;
	}

	public String getFileName() {
		return path.substring(path.lastIndexOf(System.getProperty("file.separator")) + 1);
	}
	
	public String getName() {
		String name = null;
		if (type == LogFileType.LOGFILE_SYSTEM_FILE)
			name = "File: ";
		else if (type == LogFileType.LOGFILE_ECLIPSE_CONSOLE)
			name = "Console: ";
		return name + getFileName();
	}

	public int getCount() {
		return count;
	}
	
	public void incrementCount() {
		count++;
	}
	
	public boolean equals(Object object) {
		if(object instanceof HistoryFile) {
			HistoryFile file = (HistoryFile)object;
			
			return file.getPath().equals(this.getPath()) &&
			       file.getType() == this.getType();
		}
		return false;
	}
}
