/**
 * 
 */
package com.webstarters.mailhandler.service;

import java.io.File;
import java.util.List;

/**
 * @author Aman Prasad
 *
 */
public interface FileHandler {
	
	List<File> getMailAttachments(String mailId);
	
	void deleteMailAttachmentFiles(String mailId);
	
	void addAttachmentFilesInFolder(List<File> files, String mailId);

}
