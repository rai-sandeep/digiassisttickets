package com.digiassist.ticket.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.digiassist.ticket.domain.Ticket;
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

	public Flux<Ticket> getTickets(){
		return repo.findAll();
	}
	
	public Mono<Ticket> addTicket(Ticket ticket){
		return repo.save(prepare(ticket));
	}

	private Ticket prepare(Ticket ticket) {
		String ticketId = new StringBuilder(TICKET_ID_PREFIX)
				.append(UUID.randomUUID().toString().substring(0, 7).toUpperCase()).toString();
		ticket.setTicketId(ticketId);
		ticket.setStatus(TicketStatus.NEW.toString());
		return ticket;
	}

}
