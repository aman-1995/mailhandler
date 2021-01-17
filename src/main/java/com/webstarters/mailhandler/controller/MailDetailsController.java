/**
 * 
 */
package com.webstarters.mailhandler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webstarters.mailhandler.entities.MailDetailsVo;
import com.webstarters.mailhandler.service.MailDetailsService;

/**
 * @author AmanPrasad
 *
 */

@RestController
@RequestMapping(value = "/mail")
public class MailDetailsController {

	@Autowired
	private MailDetailsService mailDetailsService = null;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveMailDetails(@RequestBody(required = true) MailDetailsVo mailDetailsVo) {
		String mailId = mailDetailsService.saveMailDetails(mailDetailsVo);
		return ResponseEntity.ok(mailId);
	}
}
