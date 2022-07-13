package com.digiassist.ticket.service;

import com.digiassist.ticket.domain.Ticket;
import com.digiassist.ticket.domain.TicketDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketService {

	public Flux<Ticket> getOpenTickets();

	public Flux<Ticket> getAllTickets();

	public Flux<Ticket> getOpenTicketsByAssignee(String assignee);

	public Flux<Ticket> getAllTicketsByAssignee(String assignee);

	public Mono<Ticket> addTicket(TicketDto ticket);

	public Mono<Ticket> updateTicket(String ticketId, TicketDto ticket);
}
