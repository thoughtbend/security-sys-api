package com.thoughtbend.securitysysapi.web.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.thoughtbend.securitysysapi.data.entity.SecUser;
import com.thoughtbend.securitysysapi.data.repository.SecUserRepository;
import com.thoughtbend.securitysysapi.web.resources.SecurityProfile;

@Controller
@RequestMapping(path="/api/login")
@ExposesResourceFor(SecurityProfile.class)
public class SecurityController {
	
	private final static Logger LOG = LoggerFactory.getLogger(SecurityController.class);
	
	@Autowired
	private SecUserRepository secUserRepository = null;
	
	private final EntityLinks entityLinks;
	
	public SecurityController(final EntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HttpEntity<Resource<SecurityProfile>> getLogin(@RequestParam("username") final String username) throws UnknownHostException {
		
		LOG.info(String.format("Call /api/login - GET (%1$s)", username));
		
		System.out.println(secUserRepository.count());
		Optional<SecUser> secUserOptional = secUserRepository.findById(1L);
		
		secUserOptional.ifPresent(secUser -> {
			System.out.println(secUser.getUsername());
		});
		
		InetAddress address = InetAddress.getLocalHost();
		
		final SecurityProfile profile = new SecurityProfile();
		
		profile.setUsername(String.format("%1$s @ %2$s", username, address.getHostName() + "/" + address.getHostAddress()));
		
		final Link selfLink = this.entityLinks.linkToSingleResource(SecurityProfile.class, username);
		
		Resource<SecurityProfile> resourceProfile = new Resource<>(profile);
		resourceProfile.add(selfLink);
		
		return new ResponseEntity<Resource<SecurityProfile>>(resourceProfile, HttpStatus.OK);
	}
}
