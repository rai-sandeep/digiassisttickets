package com.digiassist.ticket.repository;

import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;

import com.digiassist.ticket.domain.Ticket;
import com.digiassist.ticket.domain.TicketStatus;

import reactor.core.publisher.Flux;

//https://docs.spring.io/spring-cloud-gcp/docs/current/reference/html/firestore.html
public interface TicketRepository extends FirestoreReactiveRepository<Ticket> {

	Flux<Ticket> findByStatusNot(TicketStatus status);
	
	Flux<Ticket> findByAssignee(String Assignee);
	
	Flux<Ticket> findByAssigneeAndStatusNot(String Assignee, TicketStatus status);

}
