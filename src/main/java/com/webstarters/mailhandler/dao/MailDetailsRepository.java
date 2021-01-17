/**
 * 
 */
package com.webstarters.mailhandler.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.webstarters.mailhandler.entities.MailDetails;

/**
 * @author Aman Prasad
 *
 */
@Repository
public interface MailDetailsRepository extends JpaRepositoryImplementation<MailDetails, String> {

	@Query(value = "SELECT md FROM MailDetails as md WHERE md.mailSentStatus = 0")
	List<MailDetails> findByMailStatus();

	
}
