package com.thoughtbend.securitysysapi.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thoughtbend.securitysysapi.web.resources.HealthResponse;

@Controller
@RequestMapping(path="/api/security-sys-api-health")
public class HealthController {
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HealthResponse getHealth() {
		
		return new HealthResponse(true);
	}

}
