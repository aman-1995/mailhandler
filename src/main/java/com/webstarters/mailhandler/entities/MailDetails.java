/**
 * 
 */
package com.webstarters.mailhandler.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aman Prasad
 *
 */
@Entity
@Table(name = "lzc_mail_details")
public class MailDetails {

	@Id
	@Column(name = "mail_id")
	private String mailId = null;
	
	@Column(name = "mail_to")
	private String mailTo = null;
	
	@Column(name = "mail_from")
	private String mailFrom = null;
	
	@Column(name = "mail_cc")
	private String mailcc = null;
	
	@Column(name = "mail_subject")
	private String mailSubject = null;
	
	@Column(name = "mail_body")
	private String mailBody = null;
	
	@Column(name = "mail_sent_status")
	private Integer mailSentStatus = 0;
	
	@Column(name = "mail_status_message")
	private String mailStatusMessage = null;
	
	public MailDetails() {
		this.mailId = UUID.randomUUID().toString();
	}

	public MailDetails(String mailTo, String mailFrom, String mailcc, String mailBody, String mailSubject) {
		super();
		this.mailId = UUID.randomUUID().toString();
		this.mailTo = mailTo;
		this.mailFrom = mailFrom;
		this.mailcc = mailcc;
		this.mailBody = mailBody;
		this.mailSubject = mailSubject;
	}

	/**
	 * @return the mailId
	 */
	public String getMailId() {
		return mailId;
	}

	/**
	 * @param mailId the mailId to set
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
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
	 * @return the mailSentStatus
	 */
	public Integer getMailSentStatus() {
		return mailSentStatus;
	}

	/**
	 * @param mailSentStatus the mailSentStatus to set
	 */
	public void setMailSentStatus(Integer mailSentStatus) {
		this.mailSentStatus = mailSentStatus;
	}

	/**
	 * @return the mailStatusMessage
	 */
	public String getMailStatusMessage() {
		return mailStatusMessage;
	}

	/**
	 * @param mailStatusMessage the mailStatusMessage to set
	 */
	public void setMailStatusMessage(String mailStatusMessage) {
		this.mailStatusMessage = mailStatusMessage;
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
	
}
