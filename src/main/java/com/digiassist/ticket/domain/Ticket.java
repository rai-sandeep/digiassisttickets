package com.digiassist.ticket.domain;


import java.util.Date;

import org.springframework.cloud.gcp.data.firestore.Document;

import com.google.cloud.firestore.annotation.DocumentId;

import lombok.Data;

@Document
@Data
public class Ticket {

	@DocumentId
	private String ticketId;
	
	private String candidateId;
	
	private String query;
	
	private String response;
	
	private TicketStatus status;
	
	private String assignee;
	
	private String emailId;
	
	private String phoneNum;
	
	private Date createdDtm;
	
	private Date updatedDtm;
	
	private Date resolvedDtm;
}
