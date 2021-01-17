/**
 * 
 */
package com.webstarters.mailhandler.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.webstarters.mailhandler.entities.MailConfigurationEntity;

/**
 * @author Aman Prasad
 *
 */
@Repository
public interface MailConfigurationRepository extends JpaRepositoryImplementation<MailConfigurationEntity, String> {

	@Query(value = "SELECT mc FROM MailConfigurationEntity AS mc WHERE mc.configName LIKE 'mail.%'")
	List<MailConfigurationEntity> findAllMailConfiguration();

}
