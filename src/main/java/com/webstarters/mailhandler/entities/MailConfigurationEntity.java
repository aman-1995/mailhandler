/**
 * 
 */
package com.webstarters.mailhandler.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Aman Prasad
 *
 */
@Entity
@Table(name = "lzc_mail_configurations")
public class MailConfigurationEntity {

	@Id
	@Column(name = "config_id")
	private String configId = null;
	
	@Column(name = "config_name")
	private String configName = null;
	
	@Column(name = "config_value")
	private String configValue = null;
	
	@Column(name = "config_description")
	private String configDescription = null;
	
	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn = null;
	
	public MailConfigurationEntity() {

	}
	
	public MailConfigurationEntity(String configName, String configValue, String configDescription,
			Date updatedOn) {
		super();
		this.configId = UUID.randomUUID().toString();
		this.configName = configName;
		this.configValue = configValue;
		this.configDescription = configDescription;
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the configId
	 */
	public String getConfigId() {
		return configId;
	}

	/**
	 * @param configId the configId to set
	 */
	public void setConfigId(String configId) {
		this.configId = configId;
	}

	/**
	 * @return the configName
	 */
	public String getConfigName() {
		return configName;
	}

	/**
	 * @param configName the configName to set
	 */
	public void setConfigName(String configName) {
		this.configName = configName;
	}

	/**
	 * @return the configValue
	 */
	public String getConfigValue() {
		return configValue;
	}

	/**
	 * @param configValue the configValue to set
	 */
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	/**
	 * @return the configDescription
	 */
	public String getConfigDescription() {
		return configDescription;
	}

	/**
	 * @param configDescription the configDescription to set
	 */
	public void setConfigDescription(String configDescription) {
		this.configDescription = configDescription;
	}

	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
}
