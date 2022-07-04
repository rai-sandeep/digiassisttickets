package com.digiassist.ticket.service;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.digiassist.ticket.domain.Ticket;
import com.digiassist.ticket.domain.TicketDto;
import com.digiassist.ticket.domain.TicketStatus;
import com.digiassist.ticket.repository.TicketRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class TicketService {
	
	private static final String TICKET_ID_PREFIX = "REQ";
	
	private final TicketRepository repo;

	public Flux<Ticket> getOpenTickets(){
		return repo.findByStatusNot(TicketStatus.RESOLVED);
	}

	public Flux<Ticket> getAllTickets(){
		return repo.findAll();
	}

	public Flux<Ticket> getOpenTicketsByAssignee(String assignee){
		return repo.findByAssigneeAndStatusNot(assignee, TicketStatus.RESOLVED);
	}

	public Flux<Ticket> getAllTicketsByAssignee(String assignee){
		return repo.findByAssignee(assignee);
	}
	
	public Mono<Ticket> addTicket(TicketDto ticket){
		return repo.save(prepareForInsert(ticket));
	}
	
	public Mono<Ticket> updateTicket(String ticketId, TicketDto ticket){
		return repo.findById(ticketId)
	            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket not found")))
	            .map(t -> prepareForUpdate(ticket, t))
	            .flatMap(repo::save);
	}

	private Ticket prepareForUpdate(TicketDto ticket, Ticket dbTicket) {
		if (TicketStatus.RESOLVED == dbTicket.getStatus()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Resolved ticket cannot be updated");
		}	
		if (StringUtils.isNotBlank(ticket.getCandidateId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Candidate Id cannot be updated");
		}
		
		if (TicketStatus.RESOLVED == ticket.getStatus()) {
			if (StringUtils.isBlank(ticket.getResponse())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot resolve ticket without query response");
			}
			dbTicket.setResponse(ticket.getResponse());
			dbTicket.setResolvedDtm(new Date());
		}
		
		if (StringUtils.isNotBlank(ticket.getAssignee())) {
			dbTicket.setAssignee(ticket.getAssignee());
		}
		if (ticket.getStatus() != null) {
			dbTicket.setStatus(ticket.getStatus());
		}
		if (ticket.getEmailId() != null) {
			dbTicket.setEmailId(ticket.getEmailId());
		}
		if (ticket.getPhoneNum() != null) {
			dbTicket.setPhoneNum(ticket.getPhoneNum());
		}
		
		dbTicket.setUpdatedDtm(new Date());
		return dbTicket;
	}

	private Ticket prepareForInsert(TicketDto ticket) {	
		if (StringUtils.isBlank(ticket.getCandidateId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Candidate Id is needed to create ticket");
		}
		if (StringUtils.isBlank(ticket.getQuery())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Query is needed to create ticket");
		}
		
		Ticket dbTicket = new Ticket();
		String ticketId = new StringBuilder(TICKET_ID_PREFIX)
				.append(UUID.randomUUID().toString().substring(0, 7).toUpperCase()).toString();
		dbTicket.setTicketId(ticketId);
		dbTicket.setCreatedDtm(new Date());
		dbTicket.setStatus(TicketStatus.NEW);
		dbTicket.setAssignee(ticket.getAssignee());
		dbTicket.setCandidateId(ticket.getCandidateId());
		dbTicket.setQuery(ticket.getQuery());
		dbTicket.setEmailId(ticket.getEmailId());
		dbTicket.setPhoneNum(ticket.getPhoneNum());
		return dbTicket;
	}

}
