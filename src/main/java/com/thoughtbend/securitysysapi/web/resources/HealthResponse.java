package com.thoughtbend.securitysysapi.web.resources;

public class HealthResponse {

	private boolean healthy = false;

	public HealthResponse(final boolean healthy) {
		this.healthy = healthy;
	}
	
	public boolean isHealthy() {
		return healthy;
	}

	public void setHealthy(boolean healthy) {
		this.healthy = healthy;
	}
}
