package com.webstarters.mailhandler.entities;

import java.io.File;
import java.util.List;

/**
 * @author Aman Prasad
 *
 */

public class MailDetailsVo {
	
	private String mailTo = null;

	private String mailFrom = null;
	
	private String mailcc = null;
	
	private String mailBody = null;
	
	private String mailSubject = null;
	
	private List<File> attachments = null;

	public MailDetailsVo() {

	}
	
	public MailDetailsVo(MailDetails mailDetails) {
		this.mailTo = mailDetails.getMailTo();
		this.mailFrom = mailDetails.getMailFrom();
		this.mailcc = mailDetails.getMailcc();
		this.mailBody = mailDetails.getMailBody();
		this.mailSubject = mailDetails.getMailSubject();
	}

	/**
	 * @return the mailTo
	 */
	public String getMailTo() {
		return mailTo;
	}

	/**
	 * @param mailTo the mailTo to set
	 */
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	/**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}

	/**
	 * @param mailFrom the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	/**
	 * @return the mailcc
	 */
	public String getMailcc() {
		return mailcc;
	}

	/**
	 * @param mailcc the mailcc to set
	 */
	public void setMailcc(String mailcc) {
		this.mailcc = mailcc;
	}

	/**
	 * @return the mailBody
	 */
	public String getMailBody() {
		return mailBody;
	}

	/**
	 * @param mailBody the mailBody to set
	 */
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	/**
	 * @return the mailSubject
	 */
	public String getMailSubject() {
		return mailSubject;
	}

	/**
	 * @param mailSubject the mailSubject to set
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	/**
	 * @return the attachments
	 */
	public List<File> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}
	
}
