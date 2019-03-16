package com.thoughtbend.securitysysapi.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thoughtbend.securitysysapi.web.resources.SecurityProfile;

@Controller
@RequestMapping(path="/api/login")
@ExposesResourceFor(SecurityProfile.class)
public class SecurityController {
	
	private final static Logger LOG = LoggerFactory.getLogger(SecurityController.class);
	
	private final EntityLinks entityLinks;
	
	public SecurityController(final EntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HttpEntity<Resource<SecurityProfile>> getLogin(@RequestParam("username") final String username) {
		
		LOG.info(String.format("Call /api/login - GET (%1$s)", username));
		
		final SecurityProfile profile = new SecurityProfile();
		
		profile.setUsername(username);
		
		final Link selfLink = this.entityLinks.linkToSingleResource(SecurityProfile.class, username);
		
		Resource<SecurityProfile> resourceProfile = new Resource<>(profile);
		resourceProfile.add(selfLink);
		
		return new ResponseEntity<Resource<SecurityProfile>>(resourceProfile, HttpStatus.OK);
	}
}
