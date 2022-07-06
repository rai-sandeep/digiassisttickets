package com.digiassist.ticket.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digiassist.ticket.domain.Ticket;
import com.digiassist.ticket.domain.TicketDto;
import com.digiassist.ticket.service.TicketService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class TicketController {
	
	private final TicketService service;

	@GetMapping
	public Flux<Ticket> getOpenTickets(){
		return service.getOpenTickets();
	}

	@GetMapping("/all")
	public Flux<Ticket> getAllTickets(){
		return service.getAllTickets();
	}

	@GetMapping("/assignee/{assignee}")
	public Flux<Ticket> getOpenTicketsByAssignee(@PathVariable String assignee){
		return service.getOpenTicketsByAssignee(assignee);
	}

	@GetMapping("/assignee/{assignee}/all")
	public Flux<Ticket> getAllTicketsByAssignee(@PathVariable String assignee){
		return service.getAllTicketsByAssignee(assignee);
	}
	
	@PostMapping
	public Mono<Ticket> addTicket(@RequestBody TicketDto ticket){//TODO: validate request
		return service.addTicket(ticket);
	}
	

	@PostMapping("/{ticketId}")
	public Mono<Ticket> updateTicket(@PathVariable String ticketId, @RequestBody TicketDto ticket){
		return service.updateTicket(ticketId, ticket);
	}
}
