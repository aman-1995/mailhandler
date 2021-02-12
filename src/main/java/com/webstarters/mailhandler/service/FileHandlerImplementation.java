package com.webstarters.mailhandler.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webstarters.mailhandler.configuration.MailConfiguration;

@Component
public class FileHandlerImplementation implements FileHandler {
	
	@Autowired
	private MailConfiguration mailConfiguration = null;

	@Override
	public List<File> getMailAttachments(String mailId) {
		Path mailDirectory = mailConfiguration.getAttachmentDirectoryPath().resolve(mailId);
		File[] files = mailDirectory.toFile().listFiles();
		if(files != null) {
			return Arrays.asList(files);
		}
		return null;
	}

	@Override
	public void deleteMailAttachmentFiles(String mailId) {
		Path mailDirectory = mailConfiguration.getAttachmentDirectoryPath().resolve(mailId);
		try {
			if(mailDirectory.toFile().isDirectory()){
				for(File file : mailDirectory.toFile().listFiles()){
					file.delete();
				}
				Files.delete(mailDirectory);
			}else{
				Files.delete(mailDirectory);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addAttachmentFilesInFolder(List<File> files, String mailId) {
		try {
			Path mailDirectory = Files.createDirectories(mailConfiguration.getAttachmentDirectoryPath().resolve(mailId));
			for (File file : files) {
				File newFile = new File(mailDirectory.toAbsolutePath().toFile(), file.getName());
				copyFileUsingStream(file, newFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
		try (InputStream is = new FileInputStream(source); 
				OutputStream os = new FileOutputStream(dest);) {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		}
	}

}
