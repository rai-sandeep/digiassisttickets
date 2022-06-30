package com.digiassist.ticket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digiassist.ticket.domain.Ticket;
import com.digiassist.ticket.service.TicketService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("tickets")
@RequiredArgsConstructor
public class TicketController {
	
	private final TicketService service;

	@GetMapping
	public Flux<Ticket> getTickets(){
		return service.getTickets();
	}
	
	@PostMapping
	public Mono<Ticket> addTicket(@RequestBody Ticket ticket){//TODO: validate request
		return service.addTicket(ticket);
	}
}
