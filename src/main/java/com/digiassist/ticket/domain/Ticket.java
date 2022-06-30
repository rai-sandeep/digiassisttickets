package com.digiassist.ticket.domain;

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
	
	private String status;
}
