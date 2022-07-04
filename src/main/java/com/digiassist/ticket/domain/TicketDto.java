package com.digiassist.ticket.domain;


import lombok.Data;

@Data
public class TicketDto {

	private String candidateId;
	
	private String query;
	
	private String response;
	
	private TicketStatus status;
	
	private String assignee;
	
	private String emailId;
	
	private String phoneNum;
	
}
