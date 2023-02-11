package tn.esprit.spring.entity;

import javax.ws.rs.core.Response;
public class HttpResponse {
    private Response.Status status;

	public Response.Status getStatus() {
		return status;
	}

	public void setStatus(Response.Status status) {
		this.status = status;
	}
    
    
    
}
