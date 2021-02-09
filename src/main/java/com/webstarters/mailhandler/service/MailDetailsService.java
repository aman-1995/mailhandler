/**
 * 
 */
package com.webstarters.mailhandler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webstarters.mailhandler.dao.MailConfigurationRepository;
import com.webstarters.mailhandler.dao.MailDetailsRepository;
import com.webstarters.mailhandler.entities.MailConfigurationEntity;
import com.webstarters.mailhandler.entities.MailDetails;
import com.webstarters.mailhandler.entities.MailDetailsVo;

/**
 * @author Aman Prasad
 *
 */
@Service
@Transactional(readOnly = false)
public class MailDetailsService {
	
	@Autowired
	private MailDetailsRepository mailDetailsRepository = null;
	
	@Autowired
	private MailConfigurationRepository mailConfigurationRepository = null;

	@Autowired
	private FileHandler fileHandler = null;

	public String saveMailDetails(MailDetailsVo mailDetailsVo) {
		MailDetails mailDetails = new MailDetails(mailDetailsVo.getMailTo(), mailDetailsVo.getMailFrom(), 
				mailDetailsVo.getMailcc(), mailDetailsVo.getMailBody(), mailDetailsVo.getMailSubject());
		String mailId = mailDetailsRepository.save(mailDetails).getMailId();
		fileHandler.addAttachmentFilesInFolder(mailDetailsVo.getAttachments(), mailId);
		return mailId;
	}
	
	public List<MailDetails> getAllUnsentMails() {
		return mailDetailsRepository.findByMailStatus();
	}
	
	public void deleteMailById(String mailId) {
		mailDetailsRepository.deleteById(mailId);
	}
	
	public List<MailConfigurationEntity> getMailConfiguration() {
		List<MailConfigurationEntity> configurations = mailConfigurationRepository.findAllMailConfiguration();
		return configurations;
	}
}
