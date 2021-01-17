/**
 * 
 */
package com.webstarters.mailhandler.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

/**
 * @author Aman Prasad
 *
 */
@Component
public class MailConfiguration {

	private final Path attachmentDirectoryPath;

	public MailConfiguration() throws IOException {
		this.attachmentDirectoryPath = Files.createTempDirectory("mail-attachments-");
	}
	
	public Path getAttachmentDirectoryPath() {
		return attachmentDirectoryPath;
	}
	
	@PreDestroy
	private void deleteTempDirectory() throws IOException {
		Files.delete(attachmentDirectoryPath);
	}
}
