/**
 * 
 */
package com.webstarters.mailhandler.configuration;

import java.io.File;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.webstarters.mailhandler.entities.MailDetailsVo;

/**
 * @author Aman Prasad
 *
 */
public class MailUtility {

	public static Boolean sendMail(MailDetailsVo mailDetailsVo, MimeMessage message) {
		MimeMultipart mimeMultipart = new MimeMultipart();
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			message.setFrom(new InternetAddress(mailDetailsVo.getMailFrom()));
			String[] mailToAddress = mailDetailsVo.getMailTo().split(",");
			InternetAddress[] mailTo = getInternetAddresses(mailToAddress);
			message.addRecipients(RecipientType.TO, mailTo);
			if(mailDetailsVo.getMailcc() != null) {
				InternetAddress[] mailCC = getInternetAddresses(mailDetailsVo.getMailcc().split(","));
				message.addRecipients(RecipientType.CC, mailCC);
			}
			message.setSubject(mailDetailsVo.getMailSubject());
			messageBodyPart.setContent(mailDetailsVo.getMailBody(), "text/html; charset=utf-8");
			mimeMultipart.addBodyPart(messageBodyPart);
			if (mailDetailsVo.getAttachments() != null && mailDetailsVo.getAttachments().isEmpty() == false) {
				for (File attachedFile : mailDetailsVo.getAttachments()) {
					BodyPart atachmentBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(attachedFile);
					atachmentBodyPart.setDataHandler(new DataHandler(source));
					atachmentBodyPart.setFileName(attachedFile.getName());
					mimeMultipart.addBodyPart(atachmentBodyPart);
				}
			}
			message.setSentDate(new Date());
			message.setContent(mimeMultipart);
			Transport.send(message);
			return Boolean.TRUE;
		} catch (MessagingException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	private static InternetAddress[] getInternetAddresses(String[] mailAddress) {
		InternetAddress[] mailList = new InternetAddress[mailAddress.length];
		for(int counter = 0; counter < mailAddress.length; ++counter){
			try {
				mailList[counter] = new InternetAddress(mailAddress[counter]);
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		return mailList;
	}
}
