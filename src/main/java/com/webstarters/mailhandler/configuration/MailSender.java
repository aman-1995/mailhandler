/**
 * 
 */
package com.webstarters.mailhandler.configuration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.webstarters.mailhandler.entities.MailDetailsVo;
import com.webstarters.mailhandler.service.FileHandler;
import com.webstarters.mailhandler.service.MailDetailsService;

/**
 * @author Aman Prasad
 *
 */
@Component
public class MailSender {

	@Autowired
	private MailDetailsService mailDetailsService = null;

	@Autowired
	private FileHandler fileHandler = null;

	private static final String SSL = "SSL";

	private static final String TLS = "TLS";

	@Value("${mailhandler.enabled}")
	private boolean mailEnabled;

	@Scheduled(fixedRate = 5000)
	private void getMailsToBeSent() {
		if (mailEnabled) {
			HashMap<String, String> mailProperties = new HashMap<>();
			mailDetailsService.getMailConfiguration().stream().forEach(config -> {
				mailProperties.put(config.getConfigName(), config.getConfigValue());
			});

			Session session = getMailSession(mailProperties);
			mailDetailsService.getAllUnsentMails().stream().forEach(mailDetails -> {
				MimeMessage message = new MimeMessage(session);
				MailDetailsVo mailDetailsVo = new MailDetailsVo(mailDetails);
				List<File> files = fileHandler.getMailAttachments(mailDetails.getMailId());
				mailDetailsVo.setAttachments(files);
				Boolean mailSent = MailUtility.sendMail(mailDetailsVo, message);
				if (mailSent.booleanValue()) {
					mailDetailsService.deleteMailById(mailDetails.getMailId());
					fileHandler.deleteMailAttachmentFiles(mailDetails.getMailId());
				}
			});
		}
	}

	private Session getMailSession(HashMap<String, String> mailProperties) {
		Properties properties = new Properties();
		String stmpPort = mailProperties.get("mail.smtp.host");
		String smtpSecurityProtocol = mailProperties.get("mail.smtp.securityProtocol");
		properties.setProperty("mail.smtp.host", mailProperties.get("mail.smtp.host"));
		properties.setProperty("mail.smtp.port", stmpPort);
		if (smtpSecurityProtocol != null && "".equals(smtpSecurityProtocol)) {
			if (smtpSecurityProtocol.equalsIgnoreCase(SSL)) {
				properties.setProperty("mail.smtp.socketFactory.port", stmpPort);
				properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				properties.setProperty("mail.smtp.socketFactory.fallback", "true");
				properties.setProperty("mail.smtp.ssl.trust", "*");
			} else if (smtpSecurityProtocol.equalsIgnoreCase(TLS)) {
				properties.setProperty("mail.smtp.starttls.enable", "true");
			}
		}
		return getMailAuthenticatedSession(mailProperties, properties);
	}

	private Session getMailAuthenticatedSession(HashMap<String, String> mailProperties, Properties properties) {
		Session session = null;
		String smtpAutenticated = mailProperties.get("mail.smtp.auth");
		if (smtpAutenticated != null && smtpAutenticated != "" && smtpAutenticated.equals("true")) {
			Authenticator authenticator = null;
			properties.put("mail.smtp.auth", Boolean.TRUE);
			properties.setProperty("mail.smtp.user", mailProperties.get("mail.smtp.user"));
			properties.setProperty("mail.smtp.password", mailProperties.get("mail.smtp.password"));
			class SMTPAuthenticator extends javax.mail.Authenticator {
				String username = "";
				String password = "";

				protected SMTPAuthenticator(String username, String password) {
					this.username = username;
					this.password = password;
				}

				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			}
			authenticator = new SMTPAuthenticator(mailProperties.get("mail.smtp.user"),
					mailProperties.get("mail.smtp.password"));
			session = Session.getInstance(properties, authenticator);
		} else {
			session = Session.getDefaultInstance(properties, null);
			properties.put("mail.smtp.auth", Boolean.FALSE);
		}
		return session;
	}
}
